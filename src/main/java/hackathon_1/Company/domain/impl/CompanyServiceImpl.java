package hackathon_1.Company.domain.impl;


import hackathon_1.AIRequest.infrastructure.AIRequestRepository;
import hackathon_1.Company.domain.CompanyService;
import hackathon_1.Company.domain.Company;
import hackathon_1.Company.infrastructure.CompanyRepository;
import hackathon_1.Company.infrastructure.dto.CompanyConsumptionDTO;
import hackathon_1.Company.infrastructure.dto.CompanyDTO;
import hackathon_1.Company.infrastructure.dto.CompanyResponseDTO;
import hackathon_1.User.domain.Role;
import hackathon_1.User.domain.User;
import hackathon_1.User.infrastructure.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final AIRequestRepository aiRequestRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            UserRepository userRepository,
            AIRequestRepository aiRequestRepository,
            PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.aiRequestRepository = aiRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CompanyResponseDTO createCompany(CompanyDTO companyDTO) {
        if (companyRepository.findByRuc(companyDTO.getRuc()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una empresa con el RUC proporcionado");
        }

        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setRuc(companyDTO.getRuc());
        company.setAddress(companyDTO.getAddress());
        company.setContactPhone(companyDTO.getContactPhone());
        company.setContactEmail(companyDTO.getContactEmail());
        company.setActive(true);

        User admin = new User();
        admin.setUsername(companyDTO.getAdminName());
        admin.setEmail(companyDTO.getAdminEmail());
        admin.setPassword(passwordEncoder.encode(companyDTO.getAdminPassword()));
        admin.setActive(true);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.COMPANY_ADMIN);
        admin.setRoles(roles);

        company = companyRepository.save(company);

        admin.setCompany(company);
        admin = userRepository.save(admin);

        company.setAdmin(admin);
        companyRepository.save(company);

        return mapToResponseDTO(company);
    }
    @Override
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponseDTO getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public CompanyResponseDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));

        if (!company.getRuc().equals(companyDTO.getRuc()) &&
                companyRepository.findByRuc(companyDTO.getRuc()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una empresa con el RUC proporcionado");
        }

        company.setName(companyDTO.getName());
        company.setRuc(companyDTO.getRuc());
        company.setAddress(companyDTO.getAddress());
        company.setContactPhone(companyDTO.getContactPhone());
        company.setContactEmail(companyDTO.getContactEmail());

        return mapToResponseDTO(companyRepository.save(company));
    }

    @Override
    @Transactional
    public void updateCompanyStatus(Long id, boolean active) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));

        company.setActive(active);
        companyRepository.save(company);
        /*
        if (!active) {
            userRepository.deactivateAllCompanyUsers(id);
        }
        */

    }

    @Override
    public CompanyConsumptionDTO getCompanyConsumption(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));

        CompanyConsumptionDTO dto = new CompanyConsumptionDTO();
        dto.setCompanyId(company.getId());
        dto.setCompanyName(company.getName());

        // Obtener consumo total de tokens
        long totalTokens = aiRequestRepository.getTotalTokensConsumedByCompany(id);
        dto.setTotalTokensConsumed(totalTokens);

        // Obtener consumo por modelo
        List<Object[]> consumptionByModel = aiRequestRepository.getTokenConsumptionByModelForCompany(id);
        List<CompanyConsumptionDTO.ModelConsumption> modelConsumptions = new ArrayList<>();

        for (Object[] result : consumptionByModel) {
            String modelId = (String) result[0];
            Long tokensConsumed = (Long) result[1];

            CompanyConsumptionDTO.ModelConsumption modelConsumption = new CompanyConsumptionDTO.ModelConsumption();
            modelConsumption.setModelId(modelId);
            modelConsumption.setModelName(getModelName(modelId)); // Método auxiliar para obtener nombre amigable
            modelConsumption.setTokensConsumed(tokensConsumed);

            modelConsumptions.add(modelConsumption);
        }

        dto.setConsumptionByModel(modelConsumptions);
        return dto;
    }

    @Override
    public Optional<Company> findCompanyByRuc(String ruc) {
        return companyRepository.findByRuc(ruc);
    }

    private CompanyResponseDTO mapToResponseDTO(Company company) {
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setRuc(company.getRuc());
        dto.setAddress(company.getAddress());
        dto.setContactPhone(company.getContactPhone());
        dto.setContactEmail(company.getContactEmail());
        dto.setAffiliationDate(company.getAffiliationDate());
        dto.setActive(company.isActive());

        if (company.getAdmin() != null) {
            CompanyResponseDTO.UserSummaryDTO adminDto = new CompanyResponseDTO.UserSummaryDTO(
                    company.getAdmin().getId(),
                    company.getAdmin().getUsername(),
                    company.getAdmin().getEmail()
            );
            dto.setAdmin(adminDto);
        }
        dto.setTotalUsers(company.getUsers().size());

        return dto;
    }

    private String getModelName(String modelId) {
        // No tiene sentido por ahora pero cuando integremos las ia puede estar bien
        switch(modelId) {
            case "gpt-3.5-turbo":
                return "GPT-3.5 Turbo";
            case "gpt-4":
                return "GPT-4";
            case "claude-2":
                return "Claude 2";
            case "claude-3-opus":
                return "Claude 3 Opus";
            case "gemini-pro":
                return "Gemini Pro";
            default:
                return modelId;
        }
    }
}
