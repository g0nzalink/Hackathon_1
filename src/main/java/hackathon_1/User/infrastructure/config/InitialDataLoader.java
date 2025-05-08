package hackathon_1.User.infrastructure.config;

import hackathon_1.User.domain.Role;
import hackathon_1.User.domain.User;
import hackathon_1.User.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
public class InitialDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@tuempresa.com").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@tuempresa.com");
            adminUser.setPassword(passwordEncoder.encode("ContraseñaSegura123!"));
            adminUser.setFullName("Administrador del Sistema");
            adminUser.setActive(true);
            adminUser.setCreatedAt(LocalDateTime.now());

            adminUser.getRoles().add(Role.SPARKY_ADMIN);

            userRepository.save(adminUser);

            System.out.println("Usuario administrador creado con éxito.");
        }
    }

}

