package kg.attractor.headhunter.dto;

import kg.attractor.headhunter.model.RespondedApplicant;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessageDto {
    private Integer id;
    private UserDto userFromId;
    private UserDto userToId;
    private Integer respondedApplicantId;
    private String content;
    private LocalDateTime timestamp;
}