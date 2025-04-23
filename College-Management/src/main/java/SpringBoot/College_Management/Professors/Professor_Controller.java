package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/professors")
@RequiredArgsConstructor
public class Professor_Controller {

    private final Professor_Service professorService;

    @GetMapping(path = "/id/{professorId}/name/{professorName}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Professor_DTO> getProfessorByName(@PathVariable String professorId,
                                                            @PathVariable String professorName) {
        Optional<Professor_DTO> professor = professorService.getProfessorByIdAndName(professorId, professorName);
        return professor
                .map(professorDto -> ResponseEntity.ok(professorDto))
                .orElseThrow(() -> new ResourceNotFound("Professor Not found with id " + professorName+" , name "+professorName));
    }


    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Professor_DTO>> getAllProfessors(@RequestParam(defaultValue = "professorId") String sortBy) {
        return ResponseEntity.ok(professorService.getAllProfessors(sortBy));
    }

    @PostMapping(path = "/ADD")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Professor_DTO> addNewProfessor(@RequestBody @Valid Professor_DTO professorDto) {
        Professor_DTO newProfessor = professorService.addNewProfessor(professorDto);
        return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
    }

    @PutMapping(path = "/Update/id/{professorId}/name/{professorName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Professor_DTO> updateProfessor(@PathVariable String professorId,
                                                         @PathVariable String professorName,
                                                         @RequestBody @Valid Professor_DTO professorDto) {
        professorService.isExistByIdAndName(professorId,professorName);
        return ResponseEntity.ok(professorService.updateProfessor(professorId, professorName, professorDto));
    }

    @DeleteMapping(path = "/delete/id/{professorId}/name/{professorName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Boolean> deleteProfessorByName(@PathVariable String professorId,
                                                         @PathVariable String professorName) {
        boolean deleted = professorService.deleteProfessorByName(professorId,professorName);
        // Response
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/update/id/{professorId}/name/{professorName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Professor_DTO> partialUpdateProfessorByName(@PathVariable String professorId,
                                                                      @PathVariable String professorName,
                                                                      @RequestBody Map<String, Object> updates) {
        Professor_DTO update = professorService.partialUpdateProfessorByName(professorId,professorName, updates);
        if (update == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(update);
    }


}