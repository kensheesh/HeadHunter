package kg.attractor.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResumePrintDto {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String avatar;
}
