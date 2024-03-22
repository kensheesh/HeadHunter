package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Resume {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer categoryId;
    private BigDecimal salary;
    private Boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
}
