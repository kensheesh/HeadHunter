package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EducationInfoDto {
    @NotBlank(message = "Institution cannot be blank")
    private String institution;

    @NotBlank(message = "Program cannot be blank")
    private String program;

    @NotNull(message = "Start date cannot be null")
    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    @NotBlank(message = "Degree cannot be blank")
    private String degree;
}
