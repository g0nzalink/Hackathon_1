package hackathon_1.User.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;

}
