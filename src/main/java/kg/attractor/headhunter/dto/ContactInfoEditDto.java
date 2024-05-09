package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoEditDto {
    private Integer id;
    @Size(min = 1, max = 20)
    private String contactType;

    @Size(min = 1, max = 20)
    private String value;
}
