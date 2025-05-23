package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class Subject_Controller {

    private final Subject_Service subjectService;

    @GetMapping
//    @Secured("ROLE_USER")
    public ResponseEntity<List<Subject_DTO>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping(path = "/{subjectName}")
//    @Secured("ROLE_USER")
    public ResponseEntity<Subject_DTO> getSubjectById(@PathVariable String subjectName){
        Optional<Subject_DTO> student =  subjectService.getSubjectById(subjectName);
        return student
                .map(subjectDto -> ResponseEntity.ok(subjectDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with Id : " + subjectName));
    }

    @PostMapping("/ADD")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Subject_DTO> addNewSubject (@RequestBody @Valid Subject_DTO subjectDto){
        Subject_DTO newSubject = subjectService.addNewSubject(subjectDto);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);

    }

//    @PutMapping(path = "/{subjectName}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<Subject_DTO> updateSubject(@PathVariable String subjectName , @RequestBody @Valid Subject_DTO subjectDto){
//        subjectService.isExistByID(subjectName);
//        return ResponseEntity.ok(subjectService.updateSubject(subjectName,subjectDto));
//    }

    @DeleteMapping(path = "/{subjectName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Boolean> deleteSubjectById(@PathVariable String subjectName){
        boolean deleted = subjectService.deleteSubjectById(subjectName);
        // Response
        if (deleted){ return ResponseEntity.ok(true);}
        return ResponseEntity.notFound().build();
    }

}

