package kg.attractor.headhunter.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder
public class WorkExperienceInfo {
    private Integer id;
    private Integer resumeId;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
