package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Semesters.Semester_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class Course_DTO {

    private Long id;

    @NotBlank(message = "Enter course name")
    @Size(min = 3,message = "Enter course name in valid range")
    private String course;

    private Department_Entity department;

    private Set<Student_Entity> students;

    private Set<Semester_Entity> semesters;


}
