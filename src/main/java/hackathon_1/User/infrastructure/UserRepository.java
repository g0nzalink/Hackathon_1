package hackathon_1.User.infrastructure;

import hackathon_1.User.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByCompanyId(Long companyId);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.company.id = :companyId AND r = hackathon_1.User.domain.Role.COMPANY_ADMIN")
    Optional<User> findCompanyAdmin(Long companyId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
