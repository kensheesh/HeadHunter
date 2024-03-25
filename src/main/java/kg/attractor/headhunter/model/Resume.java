package kg.attractor.headhunter.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder
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
