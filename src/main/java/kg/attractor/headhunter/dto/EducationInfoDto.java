package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EducationInfoDto {
    @NotBlank(message = "{validation.resume.educationInfoInstitution}")
    private String institution;

    @NotBlank(message = "{validation.resume.educationInfoProgram}")
    private String program;

    @NotNull(message = "{validation.resume.educationInfoStartDate}")
    private LocalDate startDate;

    @NotNull(message = "{validation.resume.educationInfoEndDate}")
    private LocalDate endDate;

    @NotBlank(message = "{validation.resume.educationInfoDegree}")
    private String degree;
}
