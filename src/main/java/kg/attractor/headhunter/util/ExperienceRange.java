package kg.attractor.headhunter.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExperienceRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExperienceRange {
    String message() default "{validation.vacancy.experienceRange}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
