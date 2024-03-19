package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumes(int userId) throws UserNotFoundException;

    List<ResumeDto> getResumesByCategoryId(int categoryId) throws ResumeNotFoundException;

    List<ResumeDto> getResumesByCategoryName(String categoryName, int userId) throws ResumeNotFoundException;

    List<ResumeDto> getResumesByUserId(int userId) throws ResumeNotFoundException;

    ResumeDto getResumeById(int id) throws ResumeNotFoundException;

    List<ResumeDto> getActiveResumes();

    void createResume(ResumeDto resumeDto, int userId) throws UserNotFoundException;

    void editResume(ResumeDto resumeDto, int userId) throws UserNotFoundException;

    void deleteResumeById(int id, int userId) throws UserNotFoundException;

    List<ResumeDto> getActiveResumesByUserId(int userId) throws ResumeNotFoundException;
}
