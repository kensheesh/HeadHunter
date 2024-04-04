package kg.attractor.headhunter.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicantDto {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
}
