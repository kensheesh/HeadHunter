package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Message {
    private Integer id;
    private Integer userFromId;
    private Integer userToId;
    private Integer respondedApplicantsId;
    private String content;
    private LocalDateTime timestamp;
}
