package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespondedApplicant {
    private Integer id;
    private Integer resumeId;
    private Integer vacancyId;
    private boolean confirmation;
}
