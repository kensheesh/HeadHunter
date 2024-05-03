package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder

@Entity
@Table(name = "EDUCATIONINFO")
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "RESUMEID")
    @ManyToOne
    private Resume resume;
    private String institution;
    private String program;
    @Column(name = "STARTDATE")
    private LocalDate startDate;
    @Column(name = "ENDDATE")
    private LocalDate endDate;
    private String degree;
}
