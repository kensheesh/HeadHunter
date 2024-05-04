package kg.attractor.headhunter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Vacancy> vacancies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Resume> resumes;
}
