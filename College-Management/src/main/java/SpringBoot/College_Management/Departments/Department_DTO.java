package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Custom_Validation.Course.Validate_Course;
import SpringBoot.College_Management.Custom_Validation.Departments.Validate_Department;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.Set;

@Data
public class Department_DTO {

    private String department_Id;

    @NotBlank(message = "Department should not be blank")
    @Size(min = 3,message = "Enter department name in valid range")
//    @Validate_Department
    private String department;

//    @NotBlank(message = "Course should not be blank")
//    @Size(min = 3,message = "Enter Course in valid range")
//    @Validate_Course
    private Set<Course_Entity> course;

    private Professor_Entity HOD;

    private Professor_Entity Dean;


//    private Set<Student_Entity> students;
}