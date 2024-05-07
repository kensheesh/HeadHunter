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
public class VacancyViewAllDto {
    private Integer id;

    private UserForVacancyPrintDto user;

    private String name;

    private String description;

    private String categoryName;

    private BigDecimal salary;

    private Integer experienceFrom;

    private Integer experienceTo;

    private boolean isActive;

    private String createdDate;

    private String updateTime;
    private Integer responses;
}
