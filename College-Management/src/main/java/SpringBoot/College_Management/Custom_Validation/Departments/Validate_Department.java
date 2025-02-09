package SpringBoot.College_Management.Custom_Validation.Departments;

import SpringBoot.College_Management.Custom_Validation.Semester.Semester_Validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {Department_Validation.class})
public @interface Validate_Department {
    String message() default "Enter Correct Department in capital letters";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}