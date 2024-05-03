package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name = "RESPONDEDAPPLICANTS")
public class RespondedApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "RESUMEID")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "VACANCYID")
    private Vacancy vacancy;
    private boolean confirmation;
}
