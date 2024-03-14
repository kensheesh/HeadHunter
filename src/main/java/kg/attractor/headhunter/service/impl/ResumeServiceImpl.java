package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.ResumeDao;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.User;
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
                .userId(e.getUserId())
                .name(e.getName())
                .categoryId(e.getCategoryId())
                .salary(e.getSalary())
                .isActive(e.isActive())
                .createdTime(e.getCreatedTime())
                .updateTime(e.getUpdateTime())
                .build()));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByUserId(int userId) throws ResumeNotFoundException {
        List<Resume> resumes = resumeDao.getResumeByUserId(userId).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this userId: " + userId));
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(ResumeDto.builder()
                .id(e.getId())
                .userId(e.getUserId())
                .name(e.getName())
                .categoryId(e.getCategoryId())
                .salary(e.getSalary())
                .isActive(e.isActive())
                .createdTime(e.getCreatedTime())
                .updateTime(e.getUpdateTime())
                .build()));
        return dtos;
    }

    @Override
    public ResumeDto getResumeById(int id) throws ResumeNotFoundException {
        Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with id: " + id));
        return ResumeDto.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdTime(resume.getCreatedTime())
                .updateTime(resume.getUpdateTime())
                .build();
    }

    @Override
    public int createResume(ResumeDto resumeDto) {
        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setUserId(resumeDto.getUserId());
        resume.setName(resumeDto.getName());
        resume.setCategoryId(resumeDto.getCategoryId());
        resume.setSalary(resumeDto.getSalary());
        resume.setActive(resumeDto.isActive());
        resume.setCreatedTime(resumeDto.getCreatedTime());
        resume.setUpdateTime(resumeDto.getUpdateTime());

        return resumeDao.createResume(resume);
    }
}