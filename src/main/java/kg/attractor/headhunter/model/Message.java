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
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USERFROMID")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "USERTOID")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "RESPONDEDAPPLICANTSID")
    private RespondedApplicant respondedApplicant;

    @Column(name = "CONTENT")
    private String content;
    private LocalDateTime timestamp;
}
