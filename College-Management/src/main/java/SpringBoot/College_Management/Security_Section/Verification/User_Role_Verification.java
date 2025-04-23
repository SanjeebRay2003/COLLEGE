package SpringBoot.College_Management.Security_Section.Verification;


import SpringBoot.College_Management.Security_Section.DTOs.User_Professor_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.User_Student_DTO;
import SpringBoot.College_Management.Security_Section.Services.User_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class User_Role_Verification {

    private final User_Service userService;

    @PutMapping("/students")
    public ResponseEntity<User_Student_DTO> verifyUserAsStudent (@RequestBody Verification_DTO verificationDto){
        User_Student_DTO userDto = userService.verifyUserAsStudent(verificationDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/professors")
    public ResponseEntity<User_Professor_DTO> verifyUserAsProfessor (@RequestBody Verification_DTO verificationDto){
        User_Professor_DTO userDto = userService.verifyUserAsProfessor(verificationDto);
        return ResponseEntity.ok(userDto);
    }

}
