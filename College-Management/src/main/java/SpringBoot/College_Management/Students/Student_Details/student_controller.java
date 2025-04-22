package SpringBoot.College_Management.Students.Student_Details;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/STUDENT")
public class student_controller {

    private final student_service studentService;

    @GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.allStudents());
    }

    @GetMapping(path = "/id/{studentId}/name/{studentName}")
    @Secured("ROLE_USER")
    public ResponseEntity<Student> getStudentByName(@PathVariable String studentId,
                                                        @PathVariable String studentName) {
        Optional<Student> student = studentService.getStudentByIdAndName(studentId, studentName);
        return student
                .map(studentsDto -> ResponseEntity.ok(studentsDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with id " + studentId + " , name " + studentName));
    }

}
