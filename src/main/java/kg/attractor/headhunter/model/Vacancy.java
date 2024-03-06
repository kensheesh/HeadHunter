package kg.attractor.headhunter.model;

import java.sql.Timestamp;

public class Vacancy {
    private int id;
    private String name;
    private String description;
    private Category category;
    private int salary;
    private int experienceFrom;
    private int experienceTo;
    private boolean isActive;
    private Author author;
    private Timestamp createdDate;
    private Timestamp updateTime;
}
