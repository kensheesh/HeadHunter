package kg.attractor.headhunter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private CategoryDto categoryDto;

    @Positive
    private double salary;

    @PositiveOrZero
    private int experienceFrom;

    @PositiveOrZero
    private int experienceTo;

    private boolean isActive;

    @Positive
    private int authorId;

    @PastOrPresent
    private LocalDateTime createdDate;

    @PastOrPresent
    private LocalDateTime updateTime;
}
