package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.*;
import kg.attractor.headhunter.util.ExperienceRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ExperienceRange()
public class VacancyCreateDto {

    @NotBlank(message = "{validation.vacancy.nameBlank}")
    @Size(min = 2, max = 20, message = "{validation.vacancy.nameSize}")
    private String name;

    @NotBlank(message = "{validation.vacancy.descriptionBlank}")
    @Size(min = 5, max = 100, message = "{validation.vacancy.descriptionSize}")
    private String description;

    @NotBlank(message = "{validation.vacancy.categoryNameBlank}")
    @Size(min = 2, max = 50, message = "{validation.vacancy.categoryNameSize}")
    private String categoryName;

    @NotNull(message = "{validation.vacancy.salaryNotNull}")
    @DecimalMin(value = "0.0", message = "{validation.vacancy.salaryMin}")
    @DecimalMax(value = "1000000.0", message = "{validation.vacancy.salaryMax}")
    private BigDecimal salary;

    @NotNull(message = "{validation.vacancy.experienceFromNotNull}")
    @PositiveOrZero(message = "{validation.vacancy.experienceFromPositiveOrZero}")
    private Integer experienceFrom;

    @NotNull(message = "{validation.vacancy.experienceToNotNull}")
    @PositiveOrZero(message = "{validation.vacancy.experienceToPositiveOrZero}")
    private Integer experienceTo;

    private Boolean isActive;
}
