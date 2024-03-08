package kg.attractor.headhunter.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Message {
    private int id;
    private int respondedApplicantId;
    private String context;
    private LocalDateTime timestamp;
}
