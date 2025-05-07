package hackathon_1.Company.domain;

import hackathon_1.Company.infrastructure.dto.CompanyConsumptionDTO;
import hackathon_1.Company.infrastructure.dto.CompanyDTO;
import hackathon_1.Company.infrastructure.dto.CompanyResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyResponseDTO createCompany(CompanyDTO companyDTO);
    List<CompanyResponseDTO> getAllCompanies();
    CompanyResponseDTO getCompanyById(Long id);
    CompanyResponseDTO updateCompany(Long id, CompanyDTO companyDTO);
    void updateCompanyStatus(Long id, boolean active);
    CompanyConsumptionDTO getCompanyConsumption(Long id);
    Optional<Company> findCompanyByRuc(String ruc);
}