package SpringBoot.College_Management.Courses;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Course_DTO {

    private Long id;

    @NotBlank(message = "Enter course name")
    @Size(min = 3,message = "Enter course name in valid range")
    private String name;
}
