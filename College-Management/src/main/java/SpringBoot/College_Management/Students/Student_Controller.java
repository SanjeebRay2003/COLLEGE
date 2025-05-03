package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class Student_Controller {
    private final Student_Service studentService;


    @GetMapping(path = "/id/{studentId}/name/{studentName}")
    @Secured({"ROLE_ADMIN","ROLE_PROFESSOR"})

    public ResponseEntity<Student_DTO> getStudentByIdAndName(@PathVariable String studentId,
                                                        @PathVariable String studentName) {
        Optional<Student_DTO> student = studentService.getStudentByIdAndName(studentId, studentName);
        return student
                .map(studentsDto -> ResponseEntity.ok(studentsDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with id " + studentId + " , name " + studentName));
    }

    @GetMapping("/owner/{studentId}")
    @PreAuthorize("@owner_Of_Entity.isStudentOwner(#studentId)")
    public ResponseEntity<Student_DTO> getAllDataByOwner(@PathVariable String studentId){
        Optional<Student_DTO> student = studentService.getAllDataByOwner(studentId);
        return student
                .map(studentsDto -> ResponseEntity.ok(studentsDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with id " + studentId ));
    }



    @GetMapping // also get the sorted students
    @Secured({"ROLE_ADMIN","ROLE_PROFESSOR"})
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
