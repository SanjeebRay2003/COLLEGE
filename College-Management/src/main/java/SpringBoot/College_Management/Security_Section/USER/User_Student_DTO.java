package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Student_DTO {
    private String userId;
    private String email;
//    private String password;
    private String name;
    private Set<Roles> role;
    private String studentId;
    private String secretCode;


}
