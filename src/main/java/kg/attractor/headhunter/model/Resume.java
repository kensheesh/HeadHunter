package kg.attractor.headhunter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Resume {
    private int id;
    private int UserId;
    private String name;
    private int categoryId;
    private int salary;
    private boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
