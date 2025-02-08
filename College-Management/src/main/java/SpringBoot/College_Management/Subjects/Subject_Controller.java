package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subjects")
@RequiredArgsConstructor
public class Subject_Controller {

    private final Subject_Service subjectService;

    @GetMapping
    public ResponseEntity<List<Subject_DTO>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping(path = "/{subjectId}")
    public ResponseEntity<Subject_DTO> getSubjectById(@PathVariable Long subjectId){
        Optional<Subject_DTO> student =  subjectService.getSubjectById(subjectId);
        return student
                .map(subjectDto -> ResponseEntity.ok(subjectDto))
                .orElseThrow(() -> new ResourceNotFound("Student not found with Id : " + subjectId));
    }

    @PostMapping
    public ResponseEntity<Subject_DTO> addNewSubject (@RequestBody @Valid Subject_DTO subjectDto){
        Subject_DTO newSubject = subjectService.addNewSubject(subjectDto);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{subjectId}")
    public ResponseEntity<Subject_DTO> updateSubject(@PathVariable Long subjectId , @RequestBody @Valid Subject_DTO subjectDto){
        subjectService.isExistByID(subjectId);
        return ResponseEntity.ok(subjectService.updateSubject(subjectId,subjectDto));
    }

    @DeleteMapping(path = "/{subjectId}")
    public ResponseEntity<Boolean> deleteSubjectById(@PathVariable Long subjectId){
        boolean deleted = subjectService.deleteSubjectById(subjectId);
        // Response
        if (deleted){ return ResponseEntity.ok(true);}
        return ResponseEntity.notFound().build();
    }

}

