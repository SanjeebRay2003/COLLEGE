package SpringBoot.College_Management.Security_Section.DTOs;

import lombok.Data;

@Data
public class User_DTO {
    private Long id;
    private String email;
    //    private String password;
    private String name;
    private String role;

}
