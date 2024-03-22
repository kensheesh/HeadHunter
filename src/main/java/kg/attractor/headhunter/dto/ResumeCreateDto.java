package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.Email;
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
    private String authorEmail;

    @Size(min = 5, max = 6, message = "dfs")
    private String title;
    private String categoryName;
    private BigDecimal salary;
    private List<WorkExperienceInfoDto> workExpInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
    private Boolean isActive;
}
