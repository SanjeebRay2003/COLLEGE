package SpringBoot.College_Management.Custom_Validation.Semester;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class Semester_Validation implements ConstraintValidator<Validate_Semester,String> {
    @Override
    public boolean isValid(String semester, ConstraintValidatorContext constraintValidatorContext) {
        if (semester == null) return false;
        List<String> roles = List.of("I","II","III","IV","V","VI","VII","VIII");
        return roles.contains(semester);
    }
}
