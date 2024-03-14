package kg.attractor.headhunter.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private int id;
    private int userId;
    private String name;
    private int categoryId;
    private int salary;
    private boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
}
