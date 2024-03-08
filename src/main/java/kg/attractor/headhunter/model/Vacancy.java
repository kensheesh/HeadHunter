package kg.attractor.headhunter.model;

import java.sql.Timestamp;

public class Vacancy {
    private int id;
    private String name;
    private String description;
    private int categoryId;
    private int salary;
    private int experienceFrom;
    private int experienceTo;
    private boolean isActive;
    private int authorId;
    private Timestamp createdDate;
    private Timestamp updateTime;
}
