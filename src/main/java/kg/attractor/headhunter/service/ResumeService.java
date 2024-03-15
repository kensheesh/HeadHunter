package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.model.Resume;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumes();
    List<ResumeDto> getResumesByCategory(int categoryId) throws ResumeNotFoundException;
    List<ResumeDto> getResumesByUserId(int userId) throws ResumeNotFoundException;
    ResumeDto getResumeById(int id) throws ResumeNotFoundException;
    int createResume(ResumeDto resumeDto);
    void editResume(ResumeDto resumeDto);
    void deleteResumeById(int id);
}
