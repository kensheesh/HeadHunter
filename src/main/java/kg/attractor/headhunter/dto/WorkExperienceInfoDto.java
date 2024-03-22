package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceInfoDto {
    @PositiveOrZero
    private Integer years;

    @NotBlank
    private String companyName;

    @NotBlank
    private String position;

    private String responsibilities;
}
