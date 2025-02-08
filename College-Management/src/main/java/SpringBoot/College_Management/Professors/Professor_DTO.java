package SpringBoot.College_Management.Professors;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Professor_DTO {
    private Long professor_Id;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 3,max = 20,message = "Enter Name in valid range")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Enter valid Email")
    private String email;

    @NotBlank(message = "Contact number should not be Blank")
    @Size(min = 10,max = 10,message = "Enter 10 digit mobile number")
    private String contactNo;

    @NotNull(message = "Date of Joining should not be Null")
    @PastOrPresent(message = "Date should not be future")
    private LocalDate dateOfJoining;


    private Boolean isActive;
}


