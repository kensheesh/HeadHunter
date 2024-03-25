package kg.attractor.headhunter.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {

    @NotBlank(message = "Name cannot be null or blank")
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank(message = "Category cannot be null or blank")
    private String categoryName;

    @NotNull(message = "Salary cannot be null")
    @Min(0)
    @Max(1000000)
    private BigDecimal salary;

    @Valid
    private List<WorkExperienceInfoDto> workExpInfos;

    @Valid
    private List<EducationInfoDto> educationInfos;

    @NotEmpty(message = "Contact info list cannot be empty")
    private List<ContactInfoDto> contactInfos;

    private Boolean isActive;
}
