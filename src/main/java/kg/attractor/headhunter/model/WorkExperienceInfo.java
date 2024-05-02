package kg.attractor.headhunter.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder

@Entity
@Table(name = "workExperienceInfo", schema = "public")
public class WorkExperienceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "resumeId")
    private Resume resume;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
