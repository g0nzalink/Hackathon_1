package hackathon_1.UserLimit.infrastructure;

import hackathon_1.UserLimit.domain.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {

    List<UserLimit> findByUserId(Long userId);

    Optional<UserLimit> findByUserIdAndModelId(Long userId, String modelId);

    boolean existsByUserIdAndModelId(Long userId, String modelId);
}