package hackathon_1.AIRequest.infrastructure;

import hackathon_1.AIRequest.domain.AIRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AIRequestRepository extends JpaRepository<AIRequest, Long> {
    Page<AIRequest> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT r FROM AIRequest r WHERE r.user.company.id = :companyId")
    Page<AIRequest> findByCompanyId(Long companyId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM AIRequest r WHERE r.user.id = :userId AND r.modelId = :modelId AND r.requestTime >= :startTime")
    long countRequestsByUserAndModelInTimeWindow(Long userId, String modelId, LocalDateTime startTime);

    @Query("SELECT SUM(r.totalTokens) FROM AIRequest r WHERE r.user.id = :userId AND r.modelId = :modelId AND r.requestTime >= :startTime")
    long sumTokensByUserAndModelInTimeWindow(Long userId, String modelId, LocalDateTime startTime);

    @Query("SELECT SUM(r.totalTokens) FROM AIRequest r WHERE r.user.company.id = :companyId")
    long getTotalTokensConsumedByCompany(Long companyId);

    @Query("SELECT r.modelId, SUM(r.totalTokens) FROM AIRequest r WHERE r.user.company.id = :companyId GROUP BY r.modelId")
    List<Object[]> getTokenConsumptionByModelForCompany(Long companyId);

    @Query("SELECT SUM(r.totalTokens) FROM AIRequest r WHERE r.user.id = :userId")
    long getTotalTokensConsumedByUser(Long userId);
}