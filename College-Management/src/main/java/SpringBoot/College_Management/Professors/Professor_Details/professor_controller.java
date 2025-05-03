package SpringBoot.College_Management.Professors.Professor_Details;


import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("PROFESSOR")
public class professor_controller {

    private final professor_service professor_service;

    @GetMapping
//    @Secured({"ROLE_PROFESSOR","ROLE_STUDENT"})
    public ResponseEntity<List<Professor>> allProfessors(){
        return ResponseEntity.ok(professor_service.allProfessors());
    }

    @GetMapping(path = "/id/{professorId}/name/{professorName}")
//    @Secured({"ROLE_PROFESSOR","ROLE_STUDENT"})
    public ResponseEntity<Professor> getProfessorByIdAndName(@PathVariable String professorId,
                                                             @PathVariable String professorName)  {
        Optional<Professor> professor = professor_service.getProfessorByIdAndName(professorId, professorName);
        return professor
                .map(professorDto -> ResponseEntity.ok(professorDto))
                .orElseThrow(() -> new ResourceNotFound("Professor not found with id " + professorId + " , name " + professorName));
    }
}
