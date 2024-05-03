package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import kg.attractor.headhunter.dto.ResumeViewAllDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    void updateResumeTime(Integer resumeId, Authentication authentication);

    ResumeDto getResumeById(Integer resumeId);

    List<ResumeViewAllDto> getAllResumesOfApplicantById(Integer userId);

    Page<ResumeViewAllDto> getAllActiveResumes(int pageNumber, int pageSize, String category);

//    List<ResumeDto> getAllResumesByName(String title, Authentication authentication);
//
//    List<ResumeDto> getAllResumesByCategoryName(String categoriesName, Authentication authentication);

    List<ResumeDto> getAllResumesOfApplicant(Authentication authentication);

    void createResumeForApplicant(ResumeCreateDto resumeDto, Authentication authentication);

//    void editResumeForApplicant(ResumeEditDto resumeEditDto, Authentication authentication, Integer id);

    void deleteResumeById(Integer resumeId, Authentication authentication);
}
