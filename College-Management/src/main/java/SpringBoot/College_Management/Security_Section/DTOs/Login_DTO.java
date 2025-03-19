package SpringBoot.College_Management.Security_Section.DTOs;

import lombok.Data;

@Data
public class Login_DTO {
    private String email;
    private String password;
    private String role;
}