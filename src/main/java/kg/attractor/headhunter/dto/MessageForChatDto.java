package kg.attractor.headhunter.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageForChatDto {
    private Integer id;
    private Integer respondedApplicantId;
    private String content;
    private LocalDateTime timestamp;
}
