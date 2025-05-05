package SpringBoot.College_Management.Professors.Sorted_Professors;


import SpringBoot.College_Management.Professors.Professor_DTO;
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
@RequestMapping("/professors")
@RequiredArgsConstructor
public class Sort_Professor_Controller {

    private final Sort_Professor_Service sortProfessorService;

    @GetMapping("/name")
    public ResponseEntity<List<Professor_DTO>> getSortedProfessorByName(@RequestParam(defaultValue = "professorId") String name){
        return ResponseEntity.ok(sortProfessorService.getSortedProfessorByName(name));
    }

    @GetMapping("/isActive")
    public ResponseEntity<List<Professor_DTO>> getSortedProfessorByIsActive(@RequestParam(defaultValue = "professorId") Boolean active){
        return ResponseEntity.ok(sortProfessorService.getSortedProfessorByIsActive(active));
    }

    @GetMapping("/dateOfAdmission")
    public ResponseEntity<List<Professor_DTO>> getSortedProfessorByDateOfAdmission(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(sortProfessorService.getSortedProfessorByDateOfAdmission(start, end));
    }
}
