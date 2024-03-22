package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoDto {
    @NotBlank
    private String institution;

    @NotBlank
    private String program;

    @PastOrPresent
    private LocalDate startDate;

    @PastOrPresent
    private LocalDate endDate;

    private String degree;
}
