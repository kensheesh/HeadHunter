package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeEditDto {
    @Positive
    private Integer id;

    @NotBlank
    private String name;

    @DecimalMin("0.0")
    private BigDecimal salary;

    private List<WorkExperienceInfoEditDto> workExpInfos;
    private Boolean isActive;
//    private List<EducationInfoDto> educationInfos;
//    private List<ContactInfoDto> contactInfos;
}
