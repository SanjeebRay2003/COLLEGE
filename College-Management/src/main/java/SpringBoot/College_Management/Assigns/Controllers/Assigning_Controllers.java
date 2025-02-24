package SpringBoot.College_Management.Assigns.Controllers;

import SpringBoot.College_Management.Assigns.Services.Assigning_Services;
import SpringBoot.College_Management.Courses.Course_DTO;
import SpringBoot.College_Management.Departments.Department_DTO;
//import SpringBoot.College_Management.Semesters.Semester_DTO;
//import SpringBoot.College_Management.Semesters.Semester_Service;
//import SpringBoot.College_Management.Semesters.Semester_Service;
import SpringBoot.College_Management.Semesters.Semester_DTO;
import SpringBoot.College_Management.Semesters.Semester_Service;
import SpringBoot.College_Management.Subjects.Subject_DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Assigning_Controllers {

    private final Assigning_Services assigningServices;

    //ASSIGNING SUBJECTS TO SEMESTER ____________________________________________________________________________________________________________________________________

    private final Semester_Service semesterService;

    @PutMapping(path = "/semesters/{semester}/{subjectName}")
    public ResponseEntity<Semester_DTO> assignSubjectsToSemester(@PathVariable String semester,
                                                                 @PathVariable String subjectName){
        return ResponseEntity.ok(assigningServices.assignSubjectsToSemester(semester,subjectName));
    }

    // ASSIGNING STUDENTS TO COURSES_________________________________________________________________________________________________________________________

    @PutMapping(path = "/courses/{courseName}/student/{studentId}")
    public ResponseEntity<Course_DTO> assignCourseToStudents(@PathVariable String courseName,
                                                             @PathVariable Long studentId){
        return ResponseEntity.ok(assigningServices.assignCourseToStudents(courseName,studentId));
    }

    // ASSIGNING SEMESTER TO COURSES_________________________________________________________________________________________________________________________

    @PutMapping(path = "/courses/{courseName}/semester/{semester}")
    public ResponseEntity<Course_DTO> assignSemestersToCourse(@PathVariable String courseName,
                                                              @PathVariable String semester){
        return ResponseEntity.ok(assigningServices.assignSemestersToCourse(courseName,semester));
    }

    // ASSIGNING COURSES TO DEPARTMENT---------------------------------------------------------------------------------------------------------------------------------

    @PutMapping(path = "/departments/{departmentName}/course/{courseName}")
    public ResponseEntity<Department_DTO> assignCoursesToDepartment(@PathVariable String departmentName,
                                                                    @PathVariable String courseName){
        return ResponseEntity.ok(assigningServices.assignCoursesToDepartment(departmentName,courseName));
    }

    //ASSIGNING PROFESSORS TO SUBJECTS ____________________________________________________________________________________________________________________________________

    @PutMapping("/subjects/{subjectName}/professorId/{professorId}/name/{professorName}")
    public Subject_DTO assignProfessorsToSubjects(@PathVariable String subjectName,
                                                  @PathVariable Long professorId,
                                                  @PathVariable String professorName){
        return assigningServices.assignProfessorsToSubjects(subjectName,professorId,professorName);
    }


}
