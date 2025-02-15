package SpringBoot.College_Management.Semesters;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Custom_Validation.Semester.Validate_Semester;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Semester_DTO {

    private Long id;

    @NotBlank(message = "semester should not be blank")
    @Validate_Semester
    private String semester;

    private Course_Entity course;
}
