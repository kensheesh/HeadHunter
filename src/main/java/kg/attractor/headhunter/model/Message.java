package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

@Entity
@Table(name = "messages", schema = "public")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userFromId")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "userToId")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "respondedApplicantsId")
    private RespondedApplicant respondedApplicant;
    private String content;
    private LocalDateTime timestamp;
}
