package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeCreateDto;
//import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import kg.attractor.headhunter.exception.*;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getUsersAllResumes(int userId) throws UserNotFoundException, ResumeNotFoundException;

//    List<ResumeDto> getResumesByTitle(String title, int userId) throws ResumeNotFoundException;
//
//    List<ResumeDto> getResumesByCategoryId(int categoryId) throws ResumeNotFoundException;
//
//    List<ResumeDto> getResumesByCategoryName(String categoryName, int userId) throws ResumeNotFoundException;
//
//    List<ResumeDto> getResumesByUserId(int userId) throws ResumeNotFoundException;
//
//    ResumeDto getResumeById(int id) throws ResumeNotFoundException;
//
//    List<ResumeDto> getActiveResumes();

    void createResume(ResumeCreateDto resumeCreateDto, int userId) throws UserNotFoundException,
            CategoryNotFoundException,
            ResumeNotFoundException,
            WorkExperienceNotFoundException,
            EducationInfoNotFoundException;

    void editResume(ResumeEditDto resumeDto, int userId) throws UserNotFoundException, ResumeNotFoundException;
//
//    void deleteResumeById(int id, int userId) throws UserNotFoundException;
//
//    List<ResumeDto> getActiveResumesByUserId(int userId) throws ResumeNotFoundException;
}
