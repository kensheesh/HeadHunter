package kg.attractor.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyForRespondedDto {
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

