package kg.attractor.headhunter.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "WORKEXPERIENCEINFO")
public class WorkExperienceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "RESUMEID")
    private Resume resume;

    @Column(name = "YEARS")
    private Integer years;

    @Column(name = "COMPANYNAME")
    private String companyName;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "RESPONSIBILITIES")
    private String responsibilities;
}
