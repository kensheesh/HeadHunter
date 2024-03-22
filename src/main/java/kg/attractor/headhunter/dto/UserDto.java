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
    @Positive
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Min(18) @Max(100)
    private int age;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String phoneNumber;

    private String avatar;

    private MultipartFile file;

    private AccountType accountType;
}
