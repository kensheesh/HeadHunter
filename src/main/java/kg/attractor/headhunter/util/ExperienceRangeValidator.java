package kg.attractor.headhunter.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.headhunter.dto.VacancyCreateDto;

public class ExperienceRangeValidator implements ConstraintValidator<ExperienceRange, VacancyCreateDto> {

    @Override
    public void initialize(ExperienceRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(VacancyCreateDto dto, ConstraintValidatorContext context) {
        if (dto.getExperienceFrom() == null || dto.getExperienceTo() == null) {
            return true; // Не проверяем, если одно из значений null, это уже проверяется другими аннотациями
        }
        if (dto.getExperienceFrom() >= dto.getExperienceTo()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{validation.vacancy.experienceRange}")
                    .addPropertyNode("experienceFrom")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
