package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // Для создания конструктора без параметров
@AllArgsConstructor // Для создания конструктора со всеми параметрами
@Builder

@Entity
@Table(name = "contactsInfo", schema = "public")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "resumeId")
    @ManyToOne
    private Resume resume;

    @JoinColumn(name = "contactTypeId")
    @ManyToOne
    private ContactType contactType;
    private String content;
}
