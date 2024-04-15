package kg.attractor.headhunter.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacancyViewEditDto {
    private Integer id;

    private UserForVacancyPrintDto user;

    private String name;

    private String description;

    private String categoryName;

    private Integer salary;

    private Integer experienceFrom;

    private Integer experienceTo;

    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;
}
