package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyCreateDto {

    @NotBlank(message = "Vacancy's name cannout be null or empty")
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank(message = "Vacancy's description cannout be null or empty")
    @Size(min = 2, max = 100)
    private String description;

    @NotBlank(message = "Vacancy's description cannout be null or empty")
    @Size(min = 2, max = 100)
    private String categoryName;

    @NotNull(message = "Salary cannot be null")
    @Min(0)
    @Max(1000000)
    private BigDecimal salary;

    @PositiveOrZero
    @Max(30)
    private Integer experienceFrom;

    @PositiveOrZero
    @Max(50)
    private Integer experienceTo;

    private Boolean isActive;
}
