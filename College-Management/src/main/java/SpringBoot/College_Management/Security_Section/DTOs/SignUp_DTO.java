package SpringBoot.College_Management.Security_Section.DTOs;

import lombok.Data;

@Data
public class SignUp_DTO {
    private String name;
    private String email;
    private String role;
    private String password;
}
