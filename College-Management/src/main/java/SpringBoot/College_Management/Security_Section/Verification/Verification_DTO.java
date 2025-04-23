package SpringBoot.College_Management.Security_Section.Verification;

import lombok.Data;

@Data
public class Verification_DTO {
    private String email;
    private String secretCode;
}
