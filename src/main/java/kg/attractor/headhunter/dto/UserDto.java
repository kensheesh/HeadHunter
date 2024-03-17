package kg.attractor.headhunter.dto;

import kg.attractor.headhunter.model.AccountType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private MultipartFile file;
    private AccountType accountType;
}
