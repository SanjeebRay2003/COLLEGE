package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Students.Student_Entity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
public class Department_DTO {

    private Long department_Id;

    @NotBlank(message = "Department should not be blank")
    @Size(min = 3,message = "Enter department name in valid range")
    private String name;

    @NotBlank(message = "Course should not be blank")
    @Size(min = 3,message = "Enter Course in valid range")
    private String course;


    private List<Student_Entity> students;
}