package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "respondedApplicants")
public class RespondedApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "resumeId")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "vacancyId")
    private Vacancy vacancy;
    private boolean confirmation;
}
