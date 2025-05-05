package SpringBoot.College_Management.Security_Section.USER.Sorted_User;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import SpringBoot.College_Management.Security_Section.USER.User_DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class Sort_User_Controller {

    private final Sort_User_Service sortUserService;

    @GetMapping("/isActive")
    public ResponseEntity<List<User_DTO>> getSortedUserByIsActive(@RequestParam(defaultValue = "studentId") Boolean active){
        return ResponseEntity.ok(sortUserService.getSortedUserByIsActive(active));
    }

    @GetMapping("/name")
    public ResponseEntity<List<User_DTO>> getSortedUserByName(@RequestParam(defaultValue = "studentId") String name){
        return ResponseEntity.ok(sortUserService.getSortedUserByName(name));
    }

    @GetMapping("/role")
    public ResponseEntity<List<User_DTO>> getSortedUserByRole(@RequestParam(defaultValue = "role") Roles role){
        return ResponseEntity.ok(sortUserService.getSortedUserByRole(role));
    }

}
