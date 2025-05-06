package hackathon_1.Empresa.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import hackathon_1.Usuario.domain.Usuario;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String ruc;

    @Column(nullable = false)
    private LocalDateTime affiliationDate;

    @Column(nullable = false)
    private boolean active;


    @OneToOne
    @JoinColumn(name = "administrator_id")
    private Usuario administrator;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Usuario> users = new HashSet<>();


}

