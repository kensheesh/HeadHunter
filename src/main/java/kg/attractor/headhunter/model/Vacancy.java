package kg.attractor.headhunter.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Vacancy {
    private int id;
    private String name;
    private String description;
    private int categoryId;
    private double salary;
    private int experienceFrom;
    private int experienceTo;
    private boolean isActive;
    private int authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
