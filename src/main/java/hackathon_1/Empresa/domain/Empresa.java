package hackathon_1.Empresa.domain;

import hackathon_1.Usuario.domain.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ruc;

    private String affiliationDate;

    private boolean active;

    private List<Usuario> users;

    private List<ModelRestriction> restrictions;

}
