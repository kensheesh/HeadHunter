package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.model.Resume;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumesByCategory(int categoryId) throws ResumeNotFoundException;
}
