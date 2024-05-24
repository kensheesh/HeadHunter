package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.attractor.headhunter.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    @NotBlank(message = "{validation.register.nameBlank}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "{validation.register.nameContaining}")
    private String name;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "{validation.register.surnameContaining}")
    private String surname;

    @Email(message = "{validation.register.emailType}")
    @NotBlank(message = "{validation.register.emailBlank}")
    @Pattern(regexp = ".+@.+\\..+", message = "{validation.register.emailFormat}")
    private String email;

    @NotBlank(message = "{validation.register.passwordBlank}")
    @Size(min = 4, max = 24, message = "{validation.register.passwordLength}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "{validation.register.passwordContaining}")
    private String password;

    @NotBlank(message = "{validation.register.phoneNumberBlank}")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "{validation.register.phoneNumberFormat}")
    private String phoneNumber;

    private AccountType accountType;
}