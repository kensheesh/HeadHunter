package kg.attractor.headhunter.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
    //    @NotBlank(message = "Name cannot be null or blank")
    @Size(min = 2, max = 20)
    private String name;

    //    @NotBlank(message = "Category cannot be null or blank")
    private String categoryName;

    //    @NotNull(message = "Salary cannot be null")
    @Min(0)
    @Max(1000000)
    private BigDecimal salary;

    @Valid
    private List<WorkExperienceInfoEditDto> workExpInfos;

    @Valid
    private List<EducationInfoEditDto> educationInfos;


    private List<ContactInfoEditDto> contactInfos;

    private Boolean isActive;
}
