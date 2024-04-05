package kg.attractor.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForVacancyPrintDto {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String avatar;
}
