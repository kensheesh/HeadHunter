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

    @PositiveOrZero(message = "{validation.resume.workExpYear}")
    private Integer years;

    @NotBlank(message = "{validation.resume.workExpCompanyName}")
    private String companyName;

    @NotBlank(message = "{validation.resume.workExpPosition}")
    private String position;

    @NotBlank(message = "{validation.resume.workExpResponsibilities}")
    private String responsibilities;
}
