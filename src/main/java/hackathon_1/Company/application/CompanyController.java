package hackathon_1.Company.application;

import hackathon_1.Company.domain.CompanyService;
import hackathon_1.Company.dto.CompanyDTO;
import hackathon_1.Company.dto.CompanyResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Crear una nueva empresa con su administrador
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        CompanyResponseDTO response = companyService.createCompany(companyDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Listar todas las empresas registradas
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    /**
     * Obtener información de una empresa por ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    /**
     * Actualizar datos de la empresa
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable Long id,
                                                            @Valid @RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyDTO));
    }

    /**
     * Activar o desactivar una empresa
     */
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<Void> updateCompanyStatus(@PathVariable Long id,
                                                    @RequestParam boolean active) {
        companyService.updateCompanyStatus(id, active);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtener consumo de tokens de la empresa
     */
    @GetMapping("/{id}/consumption")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<?> getCompanyConsumption(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyConsumption(id));
    }
}

