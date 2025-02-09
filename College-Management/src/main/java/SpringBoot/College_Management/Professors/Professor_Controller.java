package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Subjects.Subject_DTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/professors")
@RequiredArgsConstructor
public class Professor_Controller {

    private final Professor_Service professorService;

    @GetMapping(path = "/{professorId}")
    public ResponseEntity<Professor_DTO> getProfessorById(@PathVariable Long professorId) {
        Optional<Professor_DTO> professor = professorService.getProfessorById(professorId);
        return professor
                .map(professorDto -> ResponseEntity.ok(professorDto))
                .orElseThrow(() -> new ResourceNotFound("Professor Not found with Id : " + professorId));
    }

    @GetMapping
    public ResponseEntity<List<Professor_DTO>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @PostMapping
    public ResponseEntity<Professor_DTO> addNewProfessor(@RequestBody Professor_DTO professorDto) {
        Professor_DTO newProfessor = professorService.addNewProfessor(professorDto);
        return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{professorId}")
    public ResponseEntity<Professor_DTO> updateProfessor(@PathVariable Long professorId, @RequestBody @Valid Professor_DTO professorDto) {
        professorService.isExistByID(professorId);
        return ResponseEntity.ok(professorService.updateProfessor(professorId, professorDto));
    }

    @DeleteMapping(path = "/{professorId}")
    public ResponseEntity<Boolean> deleteProfessorById(@PathVariable Long professorId) {
        boolean deleted = professorService.deleteProfessorById(professorId);
        // Response
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{professorId}")
    public ResponseEntity<Professor_DTO> partialUpdateProfessorById(@PathVariable Long professorId, @RequestBody Map<String, Object> updates) {
        Professor_DTO update = professorService.partialUpdateProfessorById(professorId, updates);
        if (update == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(update);
    }

    @PutMapping("/{professorId}/subject/{subjectId}")
    public ResponseEntity<Professor_DTO> assignSubjectsToProfessors(@PathVariable Long professorId,
                                                                  @PathVariable Long subjectId){
        return ResponseEntity.ok(professorService.assignSubjectsToProfessors(professorId,subjectId));
    }

}