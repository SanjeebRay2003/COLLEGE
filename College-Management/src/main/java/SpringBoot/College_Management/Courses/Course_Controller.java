package SpringBoot.College_Management.Courses;


import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
//import SpringBoot.College_Management.Semesters.Semester_DTO;
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

    @GetMapping("/{courseName}")
    public ResponseEntity<Course_DTO> getCourseByName(@PathVariable String courseName){
        Optional<Course_DTO> departmentDto = courseService.getCourseByName(courseName);
        return departmentDto
                .map(course -> ResponseEntity.ok(course))
                .orElseThrow(() -> new ResourceNotFound("department not found with id " + courseName));
    }

    @GetMapping
    public ResponseEntity<List<Course_DTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping(path = "/ADD")
    public ResponseEntity<Course_DTO> addNewCourse(@RequestBody Course_DTO courseDto){
        return new ResponseEntity<>(courseService.addNewCourse(courseDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{courseName}")
    public ResponseEntity<Boolean> deleteCourseById(@PathVariable String courseName) {
        boolean deleted = courseService.deleteCourseById(courseName);
        // Response
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }



}
