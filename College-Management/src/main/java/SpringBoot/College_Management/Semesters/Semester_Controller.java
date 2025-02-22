package SpringBoot.College_Management.Semesters;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Students.Student_DTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/semesters")
@RequiredArgsConstructor
public class Semester_Controller {

    private final Semester_Service semesterService;

    @GetMapping(path = "/{semesterId}")
    public ResponseEntity<Semester_DTO> getSemesterById(@PathVariable Long semesterId){
        Optional<Semester_DTO> semester =  semesterService.getSemesterById(semesterId);
        return semester
                .map(semesterDto -> ResponseEntity.ok(semesterDto))
                .orElseThrow(() -> new ResourceNotFound("Semester not found with Id : " + semesterId));
    }

    @PostMapping
    public ResponseEntity<Semester_DTO> addNewSemester (@RequestBody @Valid Semester_DTO semesterDto){
        Semester_DTO newSemester = semesterService.addNewSemester(semesterDto);
        return new ResponseEntity<>(newSemester, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Semester_DTO>> getAllSemester(){
        return ResponseEntity.ok(semesterService.getAllSemester());
    }

    @DeleteMapping(path = "/{semesterId}")
    public ResponseEntity<Boolean> deleteSemesterById(@PathVariable Long semesterId){
        boolean deleted = semesterService.deleteSemesterById(semesterId);
        // Response
        if (deleted){ return ResponseEntity.ok(true);}
        return ResponseEntity.notFound().build();
    }

    //ASSIGNING SUBJECTS TO SEMESTER ____________________________________________________________________________________________________________________________________

    @PutMapping(path = "/{semesterId}/subject/{subjectName}")
    public ResponseEntity<Semester_DTO> assignSubjectsToSemester(@PathVariable Long semesterId,
                                                 @PathVariable String subjectName){
        return ResponseEntity.ok(semesterService.assignSubjectsToSemester(semesterId,subjectName));
    }
}
