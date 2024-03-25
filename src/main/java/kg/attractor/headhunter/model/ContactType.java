package kg.attractor.headhunter.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder
public class ContactType {
    private Integer id;
    private String type;
}
