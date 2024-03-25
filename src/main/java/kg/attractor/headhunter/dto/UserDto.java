package kg.attractor.headhunter.dto;


import kg.attractor.headhunter.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String password;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String avatar;
    private AccountType accountType;
}
