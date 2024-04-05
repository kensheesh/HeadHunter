package kg.attractor.headhunter.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    ResumeDto getResumeById(Integer resumeId);
    List<ResumeDto> getAllResumesOfApplicantById(Integer userId);
    List<ResumeDto> getAllActiveResumes(Authentication authentication);

    List<ResumeDto> getAllResumesByName(String title, Authentication authentication);

    List<ResumeDto> getAllResumesByCategoryName(String categoriesName, Authentication authentication);

    List<ResumeDto> getAllResumesOfApplicant(Authentication authentication);

    void createResumeForApplicant(ResumeCreateDto resumeDto, Authentication authentication);

    void editResumeForApplicant(ResumeEditDto resumeEditDto, Authentication authentication, Integer id);

    void deleteResumeById(Integer resumeId, Authentication authentication);
}
