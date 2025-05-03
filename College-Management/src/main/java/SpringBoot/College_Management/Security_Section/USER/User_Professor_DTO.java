package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class User_Professor_DTO {
    private String userId;
    private String email;
    //    private String password;
    private String name;
    private Set<Roles> role;
    private String professorId;
    private String secretCode;
    private Boolean isActive;

}
