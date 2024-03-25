package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {
    @Email
    private String authorEmail;

    @NotBlank
    @Size(min = 5)
    private String name;

    @NotBlank
    private String categoryName;

    @DecimalMin("0.0")
    private BigDecimal salary;

    private List<WorkExperienceInfoDto> workExpInfos;
    private List<EducationInfoDto> educationInfos;

    @NotEmpty(message = "Contact info list cannot be empty")
    private List<ContactInfoDto> contactInfos;
    private Boolean isActive;
}
