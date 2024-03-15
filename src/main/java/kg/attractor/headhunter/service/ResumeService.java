package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumes();

    List<ResumeDto> getResumesByCategory(int categoryId) throws ResumeNotFoundException;

    List<ResumeDto> getResumesByUserId(int userId) throws ResumeNotFoundException;

    ResumeDto getResumeById(int id) throws ResumeNotFoundException;

    List<ResumeDto> getActiveResumes();

    int createResume(ResumeDto resumeDto);

    void editResume(ResumeDto resumeDto);

    void deleteResumeById(int id);

    List<ResumeDto> getActiveResumesByUserId(int userId) throws ResumeNotFoundException;
}
