package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.*;
import kg.attractor.headhunter.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {

    @Size(min = 2, max = 20, message = "Name should contain more than 3 books")
    private String name;

    @Size(min = 2, max = 20, message = "Surname should contain more than 3 books")
    private String surname;

    @Positive
    @Max(100)
    private Integer age;

    @Size(min = 4, message = "Length must be >= 4")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;

}
