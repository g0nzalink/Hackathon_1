package hackathon_1.Usuario.domain;

import hackathon_1.AiRequest.domain.AIRequest;
import hackathon_1.Empresa.domain.Empresa;
import hackathon_1.UserLimits.domain.UserLimits;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Empresa company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserLimits> limits = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AIRequest> requests = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    // Para los administradores de empresa
    @OneToOne(mappedBy = "administrator")
    private Empresa administeredCompany;

    public boolean isSparkyAdmin() {
        return roles.contains("ROLE_SPARKY_ADMIN");
    }

    public boolean isCompanyAdmin() {
        return roles.contains("ROLE_COMPANY_ADMIN");
    }
    public boolean isRegularUser() {
        return roles.contains("ROLE_USER") && !isCompanyAdmin() && !isSparkyAdmin();
    }
}
