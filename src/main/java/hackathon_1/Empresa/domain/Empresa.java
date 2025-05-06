package hackathon_1.Empresa.domain;

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

    private CompanyAdmin admin;

    private List<User> users;

    private List<ModelRestriction> restrictions;

}
