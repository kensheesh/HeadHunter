package kg.attractor.headhunter.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Resume {
    private int id;
    private int respondedApplicantId;
    private String name;
    private int categoryId;
    private int salary;
    private boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
}
