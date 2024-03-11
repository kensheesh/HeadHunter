package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.ResumeDao;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public List<ResumeDto> getResumesByCategory(int categoryId) throws ResumeNotFoundException {
        List<Resume> resumes = resumeDao.getResumesByCategory(categoryId).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this category: " + categoryId));
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(ResumeDto.builder()
                .id(e.getId())
                .respondedApplicantId(e.getUserId())
                        .name(e.getName())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .isActive(e.isActive())
                        .createdTime(e.getCreatedTime())
                        .updateTime(e.getUpdateTime())
                .build()));
        return dtos;
    }

}