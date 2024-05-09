package kg.attractor.headhunter.dto;

import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.Vacancy;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondedApplicantDtoForChat {
    private Integer id;
    private Vacancy vacancy;
    private Resume resume;
}
