package SpringBoot.College_Management.Security_Section.DTOs;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import SpringBoot.College_Management.Students.Student_Entity;
import lombok.Data;

import java.util.Set;

@Data
public class User_DTO {
    private Long id;
    private String email;
    //    private String password;
    private String name;
    private Set<Roles> role;
//    private Long studentId;

}
