package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class Department_Controller {

    private final Department_Service departmentService;


    @GetMapping("/{departmentId}")
    public ResponseEntity<Department_DTO> getDepartmentById(@PathVariable Long departmentId){
        Optional<Department_DTO> departmentDto = departmentService.getDepartmentById(departmentId);
        return departmentDto
                .map(department -> ResponseEntity.ok(department))
                .orElseThrow(() -> new ResourceNotFound("department not found with id " + departmentId));
    }

    @PostMapping
    public ResponseEntity<Department_DTO> addNewDepartment(@RequestBody  @Valid Department_DTO department){
        Department_DTO departmentDto = departmentService.addNewDepartment(department);
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department_DTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        boolean deleted = departmentService.deleteDepartmentById(departmentId);
        // Response
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{departmentId}/student/{studentId}")
        public ResponseEntity<Department_DTO> assignDepartmentToStudents(@PathVariable Long departmentId,
                                                         @PathVariable Long studentId){
        return ResponseEntity.ok(departmentService.assignDepartmentToStudents(departmentId,studentId));
    }


}
