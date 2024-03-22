package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {
    @Email
    private String authorEmail;

    @NotBlank
    private String name;

    @NotBlank
    private String categoryName;

    @DecimalMin("0.0")
    private BigDecimal salary;

    private List<WorkExperienceInfoDto> workExpInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
    private Boolean isActive;
}
