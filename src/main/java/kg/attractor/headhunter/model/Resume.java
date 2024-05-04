package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "RESUMES")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "USERID")
    @ManyToOne
    private User author;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    private Category category;

    @Column(name = "SALARY", columnDefinition = "NUMERIC(38,2)")
    private BigDecimal salary;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

    @Column(name = "CREATEDTIME")
    private LocalDateTime createdTime;

    @Column(name = "UPDATETIME")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "resume")
    private List<RespondedApplicant> respondedApplicants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<WorkExperienceInfo> workExperienceInfos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<EducationInfo> educationInfos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<ContactInfo> contactInfos;
}
