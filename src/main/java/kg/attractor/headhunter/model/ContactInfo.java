package kg.attractor.headhunter.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder
public class ContactInfo {
    private Integer id;
    private Integer resumeId;
    private Integer ContactTypeId;
    private String content;
}
