package SpringBoot.College_Management.Security_Section.DTOs;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class User_Student_DTO {
    private String userId;
    private String email;
    //    private String password;
    private String name;
    private Set<Roles> role;
    private String studentId;
    private String secretCode;

}
