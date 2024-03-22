package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Integer id;
    private Integer respondedApplicantId;
    private String content;
    private LocalDateTime timestamp;
}
