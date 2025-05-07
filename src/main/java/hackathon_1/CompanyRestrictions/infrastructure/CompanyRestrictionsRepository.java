package hackathon_1.CompanyRestrictions.infrastructure;

import hackathon_1.CompanyRestrictions.domain.CompanyRestrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRestrictionsRepository extends JpaRepository<CompanyRestrictions, Long> {
    List<CompanyRestrictions> findByCompanyId(Long companyId);

    Optional<CompanyRestrictions> findByCompanyIdAndModelId(Long companyId, String modelId);

    boolean existsByCompanyId(Long companyId);
}
