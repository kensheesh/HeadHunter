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

    @NotBlank(message = "company name cannout be null")
    private String companyName;

    @NotBlank(message = "postition cannout be null")
    private String position;

    @NotBlank(message = "responsibilities cannout be null")
    private String responsibilities;
}
