package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Courses.Enums.Semester_Enum;
import SpringBoot.College_Management.Departments.Department_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Subjects.Subject_Entity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Set;

@Data
public class Course_DTO {

    private String courseId;

    @NotBlank(message = "Enter course name")
    @Size(min = 3,message = "Enter course name in valid range")
    private String course;

    private Department_Entity department;

    @NotEmpty(message = "Enter years")
    private Integer years;

    private Set<Student_Entity> students;

    private Set<Subject_Entity> subjects;

    private Set<Semester_Enum> semesters;


}
