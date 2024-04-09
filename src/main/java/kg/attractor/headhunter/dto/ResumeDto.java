package kg.attractor.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Integer id;
    private UserResumePrintDto user;
    private String name;
    private String categoryName;
    private BigDecimal salary;
    private List<WorkExperienceInfoDto> workExpInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
    private Boolean isActive;
    private LocalDateTime updateTime;
}