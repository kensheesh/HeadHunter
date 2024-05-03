package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "CONTACTSINFO")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "RESUMEID")
    @ManyToOne
    private Resume resume;

    @JoinColumn(name = "CONTACTTYPEID")
    @ManyToOne
    private ContactType contactType;
    private String content;
}
