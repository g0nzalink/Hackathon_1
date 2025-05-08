package hackathon_1.Company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyConsumptionDTO {
    private Long companyId;
    private String companyName;
    private long totalTokensConsumed;
    private List<ModelConsumption> consumptionByModel;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModelConsumption {
        private String modelId;
        private String modelName;
        private long tokensConsumed;
    }
}