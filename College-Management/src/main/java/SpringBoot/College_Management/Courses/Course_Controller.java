package SpringBoot.College_Management.Courses;


import SpringBoot.College_Management.Departments.Department_DTO;
import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/courses")
@RequiredArgsConstructor
public class Course_Controller {

    private final Course_Service courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course_DTO> getCourseById(@PathVariable Long courseId){
        Optional<Course_DTO> departmentDto = courseService.getCourseById(courseId);
        return departmentDto
                .map(course -> ResponseEntity.ok(course))
                .orElseThrow(() -> new ResourceNotFound("department not found with id " + courseId));
    }

    @GetMapping
    public ResponseEntity<List<Course_DTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<Course_DTO> addNewCourse(@RequestBody Course_DTO courseDto){
        return new ResponseEntity<>(courseService.addNewCourse(courseDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Boolean> deleteCourseById(@PathVariable Long courseId) {
        boolean deleted = courseService.deleteCourseById(courseId);
        // Response
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    // ASSIGNING STUDENTS TO COURSES_________________________________________________________________________________________________________________________

    @PutMapping(path = "/{courseId}/student/{studentId}")
    public ResponseEntity<Course_DTO> assignCourseToStudents(@PathVariable Long courseId,
                                                                     @PathVariable Long studentId){
        return ResponseEntity.ok(courseService.assignCourseToStudents(courseId,studentId));
    }

    // ASSIGNING STUDENTS TO COURSES_________________________________________________________________________________________________________________________

    @PutMapping(path = "/{courseId}/semester/{semesterId}")
    public ResponseEntity<Course_DTO> assignSemestersToCourse(@PathVariable Long courseId,
                                                             @PathVariable Long semesterId){
        return ResponseEntity.ok(courseService.assignSemestersToCourse(courseId,semesterId));
    }
}
