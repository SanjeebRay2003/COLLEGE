package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Professors.Professor_DTO;
import SpringBoot.College_Management.Professors.Professor_Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class Subject_DTO {

    private String subjectId;

    @NotBlank(message = "Subject Name should not be blank")
    @Size(min = 3,message = "Enter valid subject name")
    private String subject;

    private Set<Professor_Entity> professors;

    private Course_Entity course;

//    private Course_Entity semester;
}
