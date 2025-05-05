package SpringBoot.College_Management.Students.Sorted_Students;

import SpringBoot.College_Management.Students.Student_DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class Sort_Students_Controller {

    private final Sort_Students_Service sortStudentsService;

    @GetMapping("/semester")
    public ResponseEntity<List<Student_DTO>> getSortedStudentBySemester(@RequestParam(defaultValue = "studentId") String sem){
        return ResponseEntity.ok(sortStudentsService.getSortedStudentBySemester(sem));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Student_DTO>> getSortedStudentByName(@RequestParam(defaultValue = "studentId") String name){
        return ResponseEntity.ok(sortStudentsService.getSortedStudentByName(name));
    }

    @GetMapping("/isActive")
    public ResponseEntity<List<Student_DTO>> getSortedStudentByIsActive(@RequestParam(defaultValue = "studentId") Boolean active){
        return ResponseEntity.ok(sortStudentsService.getSortedStudentByIsActive(active));
    }

    @GetMapping("/dateOfAdmission")
    public ResponseEntity<List<Student_DTO>> getSortedStudentByDateOfAdmission(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(sortStudentsService.getSortedStudentByDateOfAdmission(start, end));
    }

    @GetMapping("/rollNo")
    public ResponseEntity<List<Student_DTO>> getSortedStudentByRollNo(@RequestParam String start,@RequestParam String end) {
        return ResponseEntity.ok(sortStudentsService.getSortedStudentByRollNo(start, end));
    }



}
