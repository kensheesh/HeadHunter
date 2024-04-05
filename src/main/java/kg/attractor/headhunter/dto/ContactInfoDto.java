package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ContactInfoDto {
    @NotBlank
    @Size(min = 1, max = 20)
    private String contactType;
    @NotBlank
    @Size(min = 1, max = 20)
    private String value;
}
