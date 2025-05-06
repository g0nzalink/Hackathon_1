package hackathon_1.Usuario.domain;

import hackathon_1.Empresa.domain.Empresa;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Empresa company;

    private List<UserLimit> limits;

    private List<AIRequest> requests;

    private List<String> roles;
}
