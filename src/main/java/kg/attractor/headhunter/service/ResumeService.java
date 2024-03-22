package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeCreateDto;
//import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.*;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getUsersAllResumes(int userId);
    List<ResumeDto> getUsersResumeByTitle(String name, int userId) throws UserNotFoundException, ResumeNotFoundException;
    List<ResumeDto> getUsersResumesByCategoryName(String categoryName, int userId) throws UserNotFoundException, ResumeNotFoundException;
    List<ResumeDto> getUsersActiveResumes(int userId) throws UserNotFoundException, ResumeNotFoundException;

    void createResume(ResumeCreateDto resumeCreateDto, int userId) throws UserNotFoundException,
            CategoryNotFoundException,
            ResumeNotFoundException,
            WorkExperienceNotFoundException,
            EducationInfoNotFoundException;

    void editResume(ResumeEditDto resumeDto, int userId) throws UserNotFoundException, ResumeNotFoundException;
//
    void deleteResumeById(int id, int userId) throws UserNotFoundException;
    UserDto getUserById(int id) throws UserNotFoundException;
}
