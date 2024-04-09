package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkExperienceInfoDto {

    @PositiveOrZero(message = "Years for Work Experience cannot be negative")
    private Integer years;

    @NotBlank
    private String companyName;

    @NotBlank
    private String position;

    @NotBlank
    private String responsibilities;
}
