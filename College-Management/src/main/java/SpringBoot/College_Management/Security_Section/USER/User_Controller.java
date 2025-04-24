package SpringBoot.College_Management.Security_Section.USER;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class User_Controller {

    private final User_Service userService;


    @GetMapping("/{userId}")
    public ResponseEntity<User_Student_DTO> getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<User_Student_DTO>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

//    @PatchMapping("/update/{userId}")
//    public ResponseEntity<User_Student_DTO> updateUser(@PathVariable String userId,
//                                                      @RequestBody Map<String, Object> updates){
//        User_Student_DTO update =  userService.updateUser(userId,updates);
//        if (update == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(update);
//    }

    @PutMapping(path = "update/student/{userId}")
    public ResponseEntity<User_Student_DTO> updateStudentUser(@PathVariable String userId,
                                                              @RequestBody @Valid User_DTO userDto) {
        userService.isExistByUserId(userId);
        return ResponseEntity.ok(userService.updateStudentUser(userId,userDto));
    }

    @PutMapping(path = "update/professor/{userId}")
    public ResponseEntity<User_Professor_DTO> updateProfessorUser(@PathVariable String userId,
                                                              @RequestBody @Valid User_DTO userDto) {
        userService.isExistByUserId(userId);
        return ResponseEntity.ok(userService.updateProfessorUser(userId,userDto));
    }

}
