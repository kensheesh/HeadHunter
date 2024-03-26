package kg.attractor.headhunter.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyEditDto {

    @Size(min = 2, max = 20)
    private String name;

    @Size(min = 2, max = 100)
    private String description;

    @Size(min = 2, max = 100)
    private String categoryName;

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
