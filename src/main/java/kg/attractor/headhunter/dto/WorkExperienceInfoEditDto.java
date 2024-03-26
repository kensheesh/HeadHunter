package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceInfoEditDto {
    private Integer id;

    @PositiveOrZero
    private Integer years;

    @Size(min = 2, max = 30)
    private String companyName;

    @Size(min = 2, max = 30)
    private String position;

    @Size(min = 2, max = 30)
    private String responsibilities;
}
