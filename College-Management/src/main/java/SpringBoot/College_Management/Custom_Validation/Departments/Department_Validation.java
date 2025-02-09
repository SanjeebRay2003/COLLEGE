package SpringBoot.College_Management.Custom_Validation.Departments;

import SpringBoot.College_Management.Custom_Validation.Semester.Validate_Semester;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class Department_Validation implements ConstraintValidator<Validate_Department,String> {
    @Override
    public boolean isValid(String department, ConstraintValidatorContext constraintValidatorContext) {
        if (department == null) return false;
        List<String> roles = List.of("ENGINEERING AND TECHNOLOGY","MANAGEMENT","LAW","PHARMACY","BASIC AND APPLIED SCIENCE");
        return roles.contains(department);
    }
}
