package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.*;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getUsersAllResumes(int userId);

    List<ResumeDto> getUsersResumeByTitle(String name, int userId);

    List<ResumeDto> getUsersResumesByCategoryName(String categoryName, int userId);

    List<ResumeDto> getUsersActiveResumes(int userId);

    void createResume(ResumeCreateDto resumeCreateDto, int userId);

    void editResume(ResumeEditDto resumeDto, int userId);

    //
    void deleteResumeById(int id, int userId);

    UserDto getUserById(int id);
}
