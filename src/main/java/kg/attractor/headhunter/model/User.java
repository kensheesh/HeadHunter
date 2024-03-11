package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private AccountType accountType;
}
