package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class User_DTO {
    private String userId;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Enter valid Email")
    private String email;

    @NotBlank(message = "Password should not be blank")
    private String password;

    @NotBlank(message = "Name should not be blank")
    private String name;
    private Set<Roles> role;
    private String studentId;
    private String professorId;
    private String secretCode;

}
