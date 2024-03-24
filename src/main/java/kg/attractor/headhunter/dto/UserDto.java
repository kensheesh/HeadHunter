package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.*;
import kg.attractor.headhunter.model.AccountType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private AccountType accountType;
}
