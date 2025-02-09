package SpringBoot.College_Management.Custom_Validation.Course;

import SpringBoot.College_Management.Custom_Validation.Semester.Validate_Semester;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class Course_Validation implements ConstraintValidator<Validate_Course, String> {
    @Override
    public boolean isValid(String course, ConstraintValidatorContext constraintValidatorContext) {
        if (course == null) return false;
        List<String> roles = List.of(
                // ENGINEERING AND TECHNOLOGY
                "B.TECH", "BCA",
                // LAW
                "CONSTITUTION LAW", "CRIMINAL LAW",
                // MANAGEMENT
                "MARKETING MANAGEMENT", "FINANCIAL MANAGEMENT",
                // PHARMACY
                "B.PHARMA", "D.PHARMA",
                // BASIC AND APPLIED SCIENCE
                "BSC","HEALTH AND MEDICAL SCIENCE");

        return roles.contains(course);
    }
}
