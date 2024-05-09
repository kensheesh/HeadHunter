package kg.attractor.headhunter.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespondToVacancyDto {
    private Integer id;
    private Integer vacancyId;
    private Integer resumeId;
    private String message;
}
