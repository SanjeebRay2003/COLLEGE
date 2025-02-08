package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class Student_Controller {
    private final Student_Service studentService;

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<Student_DTO> getStudentById(@PathVariable Long studentId){
        Optional<Student_DTO> student =  studentService.getStudentById(studentId);
        return student
                .map(studentsDto -> ResponseEntity.ok(studentsDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with Id : " + studentId));
    }

    @GetMapping
    public ResponseEntity<List<Student_DTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student_DTO> addNewStudent (@RequestBody @Valid Student_DTO studentsDto){
        Student_DTO newStudent = studentService.addNewStudent(studentsDto);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{studentId}")
    public ResponseEntity<Student_DTO> updateStudent(@PathVariable Long studentId , @RequestBody @Valid Student_DTO studentsDto){
        studentService.isExistByID(studentId);
        return ResponseEntity.ok(studentService.updateStudent(studentId,studentsDto));
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable Long studentId){
        boolean deleted = studentService.deleteStudentById(studentId);
        // Response
        if (deleted){ return ResponseEntity.ok(true);}
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{studentId}")
    public ResponseEntity<Student_DTO> partialUpdateStudentById(@PathVariable Long studentId,@RequestBody Map<String, Object> updates){
        Student_DTO update =  studentService.partialUpdateStudentById(studentId,updates);
        if (update == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(update);
    }
}
