package SpringBoot.College_Management.Security_Section.DTOs;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class SignUp_DTO {
    private String name;
    private String email;
    private Set<Roles> role;
    private String password;
}
