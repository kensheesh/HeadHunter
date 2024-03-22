package kg.attractor.headhunter.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactInfoDto {
    private String contactType;
    private String value;
}
