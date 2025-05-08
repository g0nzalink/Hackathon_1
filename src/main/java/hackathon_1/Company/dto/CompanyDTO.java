package hackathon_1.Company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String name;

    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "^[0-9]{11}$", message = "El RUC debe tener 11 dígitos")
    private String ruc;

    private String address;

    @Pattern(regexp = "^[0-9]{9,15}$", message = "Número de teléfono inválido")
    private String contactPhone;

    @Email(message = "Email inválido")
    private String contactEmail;

    @NotBlank(message = "El nombre del administrador es obligatorio")
    private String adminName;

    @NotBlank(message = "El email del administrador es obligatorio")
    @Email(message = "Email del administrador inválido")
    private String adminEmail;

    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String adminPassword;
}
