//package SpringBoot.College_Management.Semesters;
//
//import SpringBoot.College_Management.Courses.Course_Entity;
//import SpringBoot.College_Management.Custom_Validation.Semester.Validate_Semester;
//import SpringBoot.College_Management.Subjects.Subject_Entity;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotBlank;
//import lombok.Data;
//
//import java.util.Set;
//
//@Data
//public class Semester_DTO {
//
//
//    private Long id;
//
//    @NotBlank(message = "semester should not be blank")
//    @Validate_Semester
//    private String semester;
//
//    private Set<Course_Entity> course;
//
//    private Set<Subject_Entity> subjects;
//}
