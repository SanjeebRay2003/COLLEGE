package SpringBoot.College_Management.Custom_Validation.Semester;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {Semester_Validation.class})
public @interface Validate_Semester {
    String message() default "Enter Semester in roman number of valid correct semester";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}
