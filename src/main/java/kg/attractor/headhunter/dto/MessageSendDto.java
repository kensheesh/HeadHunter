package kg.attractor.headhunter.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageSendDto {
    private Integer userFromId;
    private Integer userToId;
    private Integer respondedApplicantsId;
    private String content;
    private LocalDateTime timestamp;
}
