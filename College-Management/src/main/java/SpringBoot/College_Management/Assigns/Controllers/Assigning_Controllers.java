package SpringBoot.College_Management.Assigns.Controllers;

import SpringBoot.College_Management.Assigns.Services.Assigning_Services;
import SpringBoot.College_Management.Courses.Course_DTO;
import SpringBoot.College_Management.Departments.Department_DTO;
//import SpringBoot.College_Management.Semesters.Semester_Service;
import SpringBoot.College_Management.Subjects.Subject_DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/assign")
public class Assigning_Controllers {

    private final Assigning_Services assigningServices;

    //ASSIGNING SUBJECTS TO SEMESTER ____________________________________________________________________________________________________________________________________

//    @PutMapping(path = "/courses/{courseName}/{subjectName}")
//    public ResponseEntity<Course_DTO> assignSubjectsToSemester(@PathVariable String courseName,
//                                                               @PathVariable String semester){
//        return ResponseEntity.ok(assigningServices.assignSubjectsToSemester(courseName,semester));
//    }

    // ASSIGNING STUDENTS TO COURSES_________________________________________________________________________________________________________________________

    @PutMapping(path = "/courses/{courseName}/student/{studentId}/{studentName}")
    public ResponseEntity<Course_DTO> assignCourseToStudents(@PathVariable String courseName,
                                                             @PathVariable String studentId,
                                                             @PathVariable String studentName){
        return ResponseEntity.ok(assigningServices.assignCourseToStudents(courseName,studentId,studentName));
    }

    // ASSIGNING SEMESTER TO COURSES_________________________________________________________________________________________________________________________

//    @PutMapping(path = "/courses/{courseName}/semester/{semester}")
//    public ResponseEntity<Course_DTO> assignSemestersToCourse(@PathVariable String courseName,
//                                                              @PathVariable String semester){
//        return ResponseEntity.ok(assigningServices.assignSemestersToCourse(courseName,semester));
//    }

    // ASSIGNING COURSES TO DEPARTMENT---------------------------------------------------------------------------------------------------------------------------------

    @PutMapping(path = "/departments/{departmentName}/course/{courseName}")
    public ResponseEntity<Department_DTO> assignCoursesToDepartment(@PathVariable String departmentName,
                                                                    @PathVariable String courseName){
        return ResponseEntity.ok(assigningServices.assignCoursesToDepartment(departmentName,courseName));
    }

    //ASSIGNING PROFESSORS TO SUBJECTS ____________________________________________________________________________________________________________________________________

    @PutMapping("/subjects/{subjectName}/professorId/{professorId}/name/{professorName}")
    public Subject_DTO assignProfessorsToSubjects(@PathVariable String subjectName,
                                                  @PathVariable String professorId,
                                                  @PathVariable String professorName){
        return assigningServices.assignProfessorsToSubjects(subjectName,professorId,professorName);
    }

    //ASSIGNING SUBJECTS TO SEMESTER ____________________________________________________________________________________________________________________________________

    @PutMapping(path = "courses/{courseName}/subjects/{subjectName}")
    public Course_DTO assignSubjectsToCourses(@PathVariable String courseName,
                                              @PathVariable String subjectName){
        return assigningServices.assignSubjectsToCourses(courseName,subjectName);
    }

    //ASSIGNING HOD TO DEPARTMENT ____________________________________________________________________________________________________________________________________

    @PutMapping(path = "/departments/{department}/hodId/{HodId}/HOD/{HOD}")
    public Department_DTO Hod (@PathVariable String department,
                               @PathVariable String HodId,
                               @PathVariable String HOD){

        return assigningServices.Hod(department,HodId,HOD);
    }

    @PutMapping(path = "departments/{department}/deanId/{deanId}/Dean/{dean}")
    public Department_DTO Dean (@PathVariable String department,
                               @PathVariable String deanId,
                               @PathVariable String dean){

        return assigningServices.Dean(department,deanId,dean);
    }


}
