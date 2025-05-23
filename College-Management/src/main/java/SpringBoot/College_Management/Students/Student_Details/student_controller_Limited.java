package SpringBoot.College_Management.Students.Student_Details;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Security_Section.USER.User_Student_DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/STUDENT")
public class student_controller_Limited {

    private final student_service studentService;

    @GetMapping
//    @Secured({"ROLE_USER","ROLE_STUDENT","ROLE_ADMIN"})
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.allStudents());
    }

    @GetMapping(path = "/id/{studentId}/name/{studentName}")
//    @Secured({"ROLE_USER","ROLE_STUDENT","ROLE_ADMIN"})
    public ResponseEntity<Student> getStudentByName(@PathVariable String studentId,
                                                        @PathVariable String studentName) {
        Optional<Student> student = studentService.getStudentByIdAndName(studentId, studentName);
        return student
                .map(studentsDto -> ResponseEntity.ok(studentsDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with id " + studentId + " , name " + studentName));
    }

    @GetMapping("userDetails/{studentId}")
    public ResponseEntity<User_Student_DTO> getStudentsUserDetails(@PathVariable String studentId){
        return ResponseEntity.ok(studentService.getStudentsUserDetails(studentId));
    }

}
