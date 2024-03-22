package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondedApplicantDto {
    @Positive
    private int id;

    @Positive
    private int resumeId;

    @Positive
    private int vacancyId;

    private boolean confirmation;
}
