package SpringBoot.College_Management.Subjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Subject_DTO {

    private Long subject_Id;

    @NotBlank(message = "Subject Name should not be blank")
    @Size(min = 3,message = "Enter valid subject name")
    private String subject_Name;
}
