package SpringBoot.College_Management.Security_Section.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login_Response_DTO {

    private Long userId;
    private String role;
    private String accessToken;
    private String refreshToken;
}
