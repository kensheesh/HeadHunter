package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacancyDto {
    private Integer id;

    private UserForVacancyPrintDto user;

    private String name;

    private String description;

    private String categoryName;

    private BigDecimal salary;

    private Integer experienceFrom;

    private Integer experienceTo;

    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;
}
