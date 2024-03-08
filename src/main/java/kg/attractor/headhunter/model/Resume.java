package kg.attractor.headhunter.model;

import java.sql.Timestamp;

public class Resume {
    private int id;
    private int respondedApplicantId;
    private String name;
    private int categoryId;
    private int salary;
    private boolean isActive;
    private Timestamp createdTime;
    private Timestamp updateTime;
}
