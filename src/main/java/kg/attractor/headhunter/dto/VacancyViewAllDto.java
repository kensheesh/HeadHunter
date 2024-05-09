package kg.attractor.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
