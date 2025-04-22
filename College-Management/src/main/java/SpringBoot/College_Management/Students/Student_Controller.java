package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Subjects.Subject_Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class Student_Controller {
    private final Student_Service studentService;
    private final Subject_Service subjectService;

    @GetMapping(path = "/id/{studentId}/name/{studentName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Student_DTO> getStudentByIdAndName(@PathVariable String studentId,
                                                        @PathVariable String studentName) {
        Optional<Student_DTO> student = studentService.getStudentByIdAndName(studentId, studentName);
        return student
                .map(studentsDto -> ResponseEntity.ok(studentsDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with id " + studentId + " , name " + studentName));
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("@student_Service.getStudentByEmail(#email)")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<Student_DTO> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));

    }

//    @GetMapping(path = "/{id}") // only for checking the owner of the entity
//    public ResponseEntity<Student_DTO> getStudentById(@PathVariable Long studentId){
//      Student_DTO student = studentService.getStudentById(studentId);
//      return ResponseEntity.ok(student);
//    }

    @GetMapping // also get the sorted students
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Student_DTO>> getAllStudents(@RequestParam(defaultValue = "studentId") String sortBy) {
        return ResponseEntity.ok(studentService.getAllStudents(sortBy));
    }

    @PostMapping(path = "/ADD")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Student_DTO> addNewStudent (@RequestBody @Valid Student_DTO studentsDto){
        Student_DTO newStudent = studentService.addNewStudent(studentsDto);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }


    @PutMapping(path = "Update/id/{studentId}/name/{studentName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Student_DTO> updateStudent(@PathVariable String studentId,
                                                     @PathVariable String studentName,
                                                     @RequestBody @Valid Student_DTO studentsDto) {
        studentService.isExistByIdAndName(studentId,studentName);
        return ResponseEntity.ok(studentService.updateStudent(studentId,studentName,studentsDto));
    }

    @DeleteMapping(path = "/delete/id/{studentId}/name/{studentName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable String studentId,
                                                     @PathVariable String studentName){
        boolean deleted = studentService.deleteStudentById(studentId,studentName);
        // Response
        if (deleted){ return ResponseEntity.ok(true);}
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/update/id/{studentId}/name/{studentName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Student_DTO> partialUpdateStudentById(@PathVariable String studentId,
                                                                @PathVariable String studentName,
                                                                @RequestBody Map<String, Object> updates){
        Student_DTO update =  studentService.partialUpdateStudentById(studentId,studentName,updates);
        if (update == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(update);
    }
    

}
