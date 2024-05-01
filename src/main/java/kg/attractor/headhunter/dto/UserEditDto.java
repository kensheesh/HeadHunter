package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {
    @Size(min = 2, max = 20, message = "Name should contain more than 2 letters")
    private String name;

    @Size(min = 2, max = 20, message = "Surname should contain more than 2 letters")
    private String surname;

    @Min(18)
    @Max(100)
    private Integer age;

    @Size(min = 4, message = "Length must be >= 4")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;
}
