package SpringBoot.College_Management.Security_Section.USER;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class User_Controller {

    private final User_Service userService;

    @GetMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User_DTO> getStudentUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User_DTO>> getAllStudentUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }


//    @GetMapping("/student/{userId}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<User_Student_DTO> getStudentUserById(@PathVariable String userId){
//        return ResponseEntity.ok(userService.getStudentUserById(userId));
//    }
//
//    @GetMapping("/students")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<List<User_Student_DTO>> getAllStudentUser(){
//        return ResponseEntity.ok(userService.getAllStudentUser());
//    }

//    @GetMapping("/professor/{userId}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<User_Professor_DTO> getProfessorUserById(@PathVariable String userId){
//        return ResponseEntity.ok(userService.getProfessorUserById(userId));
//    }
//
//    @GetMapping("/professors")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<List<User_Professor_DTO>> getAllProfessorUser(){
//        return ResponseEntity.ok(userService.getAllProfessorUser());
//    }

    @PatchMapping("/update/student/{userId}")
    public ResponseEntity<User_Student_DTO> partialUpdateStudentUser(@PathVariable String userId,
                                                      @RequestBody Map<String, Object> updates){
        User_Student_DTO update =  userService.partialUpdateStudentUser(userId,updates);
        return ResponseEntity.ok(update);
    }

    @PatchMapping("/update/professor/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User_Professor_DTO> partialUpdateProfessorUser(@PathVariable String userId,
                                                       @RequestBody Map<String, Object> updates){
        User_Professor_DTO update =  userService.partialUpdateProfessorUser(userId,updates);
        return ResponseEntity.ok(update);
    }

    @PutMapping(path = "update/student/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User_Student_DTO> updateStudentUser(@PathVariable String userId,
                                                              @RequestBody @Valid User_DTO userDto) {
        userService.isExistByUserId(userId);
        return ResponseEntity.ok(userService.updateStudentUser(userId,userDto));
    }

    @PutMapping(path = "update/professor/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User_Professor_DTO> updateProfessorUser(@PathVariable String userId,
                                                              @RequestBody @Valid User_DTO userDto) {
        userService.isExistByUserId(userId);
        return ResponseEntity.ok(userService.updateProfessorUser(userId,userDto));
    }

}
