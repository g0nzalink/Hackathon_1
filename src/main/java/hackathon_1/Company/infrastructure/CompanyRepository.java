package hackathon_1.Company.infrastructure;

import hackathon_1.Company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByRuc(String ruc);

    boolean existsByRuc(String ruc);
}
