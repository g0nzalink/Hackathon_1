package hackathon_1.Company.domain;

import hackathon_1.CompanyRestrictions.domain.CompanyRestrictions;
import hackathon_1.User.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String ruc;

    @Column(nullable = false)
    private LocalDateTime affiliationDate;

    @Column(nullable = false)
    private boolean active = true;

    private String address;
    private String contactPhone;
    private String contactEmail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User admin;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyRestrictions> restrictions = new HashSet<>();

    @PrePersist
    public void prePersist() {
        affiliationDate = LocalDateTime.now();
    }
}