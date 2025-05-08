package hackathon_1.CompanyRestrictions.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRestrictionsDTO {

    @NotNull(message = "El ID es obligatorio")
    private Long id;
    @NotNull(message = "La empresa es obligatoria")
    private Long companyId;
    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String companyName;
    @NotBlank(message = "El tipo del modelo es obligatorio")
    private String modelType;
    @NotBlank(message = "El ID del modelo es obligatorio")
    private String modelId;
    @NotBlank(message = "El nombre del modelo es obligatorio")
    private String modelName;

    @Min(value = 0, message = "El número máximo de solicitudes por día no puede ser negativo")
    private int maxRequestsPerTime;

    private int timeWindowInMinutes;

    @Min(value = 0, message = "El número máximo de tokens por día no puede ser negativo")
    private long maxTokensPerTime;

    @NotNull(message = "Debe especificar si el modelo está habilitado")
    private boolean active;
}
