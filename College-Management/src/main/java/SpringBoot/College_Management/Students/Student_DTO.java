package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Courses.Enums.Semester_Enum;
import SpringBoot.College_Management.Custom_Validation.Semester.Validate_Semester;
import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Security_Section.User_Entity;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Student_DTO {
    private String studentId;

    @NotBlank(message = "Roll number should not be blank")
    @Size(min = 10, max = 10,message = "Enter 10 digit Roll Number")
    private String rollNo;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 3,max = 20,message = "Enter Name in valid range")
    private String name;


    @NotBlank(message = "semester should not be blank")
////    @Size(min = 1,max = 3,message = "Enter semester in valid range")
    @Validate_Semester
    private String semester;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Enter valid Email")
    private String email;

    @NotBlank(message = "Contact number should not be Blank")
    @Size(min = 10,max = 10,message = "Enter 10 digit mobile number")
    private String contactNo;

    @NotNull(message = "Admission date should not be Null")
    @PastOrPresent(message = "Date should not be future")
    private LocalDate dateOfAdmission;
    private Course_Entity course;
    private String secretCode;

//    private User_Entity owner;
}
