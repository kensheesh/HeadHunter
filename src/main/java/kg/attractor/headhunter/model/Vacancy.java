package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "categoryId")
    private Category category;
    private BigDecimal salary;
    private Integer experienceFrom;
    private Integer experienceTo;
    private Boolean isActive;

    @JoinColumn(name = "authorId")
    @ManyToOne
    private User author;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
