package kg.attractor.headhunter.dto;


import kg.attractor.headhunter.model.Message;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyMessagesAndOtherInfoDto {
    private Integer id;
    private UserDto author;
    private List<MessageForChatDto> messages;
}
