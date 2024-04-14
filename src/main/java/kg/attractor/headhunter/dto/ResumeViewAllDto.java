package kg.attractor.headhunter.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeViewAllDto {
    private Integer id;
    private UserResumePrintDto user;
    private String name;
    private String categoryName;
    private BigDecimal salary;
    private List<WorkExperienceInfoDto> workExpInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
    private Boolean isActive;
    private String updateTime;
}
