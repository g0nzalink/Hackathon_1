package hackathon_1.Company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String ruc;
    private String address;
    private String contactPhone;
    private String contactEmail;
    private LocalDateTime affiliationDate;
    private boolean active;
    private UserSummaryDTO admin;
    private int totalUsers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummaryDTO {
        private Long id;
        private String name;
        private String email;
    }
}