package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllActiveResumes(Authentication authentication);

    List<ResumeDto> getAllResumesByName(String title, Authentication authentication);

    List<ResumeDto> getAllResumesByCategoryName(String categoriesName, Authentication authentication);

    List<ResumeDto> getAllResumesOfApplicant(Authentication authentication);

    void createResumeForApplicant(ResumeCreateDto resumeDto, Authentication authentication);

    void editResumeForApplicant(ResumeEditDto resumeEditDto, Authentication authentication);
//    List<ResumeDto> getUsersResumeByTitle(String name, int userId);
//
//    List<ResumeDto> getUsersResumesByCategoryName(String categoryName, int userId);
//
//    List<ResumeDto> getUsersActiveResumes(int userId);
//
//    void createResume(ResumeCreateDto resumeCreateDto, int userId);
//
//    void editResume(ResumeEditDto resumeDto, int userId);
//
//    //
//    void deleteResumeById(int id, int userId);

//    UserDto getUserById(int id);
}
