package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
