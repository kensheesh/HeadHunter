package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "VACANCIES")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    private Category category;

    @Column(name = "SALARY", columnDefinition = "NUMERIC(38,2)")
    private BigDecimal salary;

    @Column(name = "EXPERIENCEFROM")
    private Integer experienceFrom;

    @Column(name = "EXPERIENCETO")
    private Integer experienceTo;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

    @JoinColumn(name = "AUTHORID")
    @ManyToOne
    private User author;

    @Column(name = "CREATEDDATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATETIME")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "vacancy")
    private List<RespondedApplicant> respondedApplicants;

}
