package kg.attractor.headhunter.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {

    @NotBlank(message = "{validation.resume.nameBlank}")
    @Size(min = 2, max = 20, message = "{validation.resume.nameSize}")
    private String name;

    @NotBlank(message = "{validation.vacancy.categoryNameBlank}")
    @Size(min = 2, max = 50, message = "{validation.vacancy.categoryNameSize}")
    private String categoryName;

    @NotNull(message = "{validation.vacancy.salaryNotNull}")
    @DecimalMin(value = "0.0", message = "{validation.vacancy.salaryMin}")
    @DecimalMax(value = "1000000.0", message = "{validation.vacancy.salaryMax}")
    private BigDecimal salary;

    @Valid
    private List<WorkExperienceInfoDto> workExpInfos = new ArrayList<>();
    @Valid
    private List<EducationInfoDto> educationInfos = new ArrayList<>();
    @Valid
    private List<ContactInfoDto> contactInfos = new ArrayList<>();

    private Boolean isActive;
}
