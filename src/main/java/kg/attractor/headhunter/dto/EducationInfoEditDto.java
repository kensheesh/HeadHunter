package kg.attractor.headhunter.dto;


import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoEditDto {
    private Integer id;

    @Size(min = 2, max = 30)
    private String institution;

    @Size(min = 2, max = 30)
    private String program;

    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    //    @Size(min = 2, max = 30)
    private String degree;
}
