package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.ResumeDao;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final UserDao userDao;

    @Override
    public List<ResumeDto> getResumes(int userId) throws UserNotFoundException {
        Optional<User> user = userDao.getUserById(userId);

        if (userId > userDao.getUsers().size() || userId < 1) {
            log.error("UserNotFoundException for ID: {}", userId);
            throw new UserNotFoundException("Don't have access");
        }
        if (user.isPresent()) {
            if (user.get().getAccountType().name().equals("APPLICANT")) {
                log.error("Access denied for applicant with ID: {}", userId);

                throw new UserNotFoundException("Don't have access");
            }
        }

        List<Resume> resumes = resumeDao.getResumes();
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
    public List<ResumeDto> getResumesByTitle(String title, int userId) throws ResumeNotFoundException {
        Optional<User> user = userDao.getUserById(userId);

        if (userId > userDao.getUsers().size() || userId < 1) {
            log.error("UserNotFoundException for ID: {}", userId);
            throw new ResumeNotFoundException("Don't have access");
        }
        if (user.isPresent()) {
            if (user.get().getAccountType().name().equals("APPLICANT")) {
                log.error("Access denied for applicant with ID: {}", userId);

                throw new ResumeNotFoundException("Don't have access");
            }
        }

        List<Resume> resumes = resumeDao.getResumesByTitle(title);
        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException("Can't find resumes with title: " + title);
        }
        return resumes.stream().map(resume -> ResumeDto.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdTime(resume.getCreatedTime())
                .updateTime(resume.getUpdateTime())
                .build()).collect(Collectors.toList());
    }


    @Override
    public List<ResumeDto> getResumesByCategoryId(int categoryId) throws ResumeNotFoundException {
        List<Resume> resumes = resumeDao.getResumesByCategoryId(categoryId);
        if (resumes.isEmpty() || categoryId == 0) {
            throw new ResumeNotFoundException("Can't find resume with this category: " + categoryId);
        }

        return resumes.stream().map(resume -> ResumeDto.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdTime(resume.getCreatedTime())
                .updateTime(resume.getUpdateTime())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getResumesByCategoryName(String categoryName, int userId) throws ResumeNotFoundException {
        Optional<User> user = userDao.getUserById(userId);

        if (userId > userDao.getUsers().size() || userId < 1) {
            throw new ResumeNotFoundException("Don't have access");
        }
        if (user.isPresent()) {
            if (user.get().getAccountType().name().equals("APPLICANT")) {
                throw new ResumeNotFoundException("Don't have access");
            }
        }

        List<Resume> resumes = resumeDao.getResumesByCategoryName(categoryName);
        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException("Can't find resume with this category: " + categoryName);
        }

        return resumes.stream().map(resume -> ResumeDto.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdTime(resume.getCreatedTime())
                .updateTime(resume.getUpdateTime())
                .build()).collect(Collectors.toList());
    }


    @Override
    public List<ResumeDto> getActiveResumes() {
        List<Resume> resumes = resumeDao.getActiveResumes();
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
    public List<ResumeDto> getActiveResumesByUserId(int userId) throws ResumeNotFoundException {
        List<Resume> resumes = resumeDao.getActiveResumesByUserId(userId);
        List<ResumeDto> dtos = new ArrayList<>();
        if (resumes.isEmpty() || userId == 0) {
            throw new ResumeNotFoundException("Can't find resume with this userId: " + userId);
        }

        return resumes.stream().map(resume -> ResumeDto.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdTime(resume.getCreatedTime())
                .updateTime(resume.getUpdateTime())
                .build()).collect(Collectors.toList());
    }


    @Override
    public List<ResumeDto> getResumesByUserId(int userId) throws ResumeNotFoundException {
        List<Resume> resumes = resumeDao.getResumesByUserId(userId);
        if (resumes.isEmpty() || userId == 0) {
            throw new ResumeNotFoundException("Can't find resume with this category: " + userId);
        }

        return resumes.stream().map(resume -> ResumeDto.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdTime(resume.getCreatedTime())
                .updateTime(resume.getUpdateTime())
                .build()).collect(Collectors.toList());
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
    public void createResume(ResumeDto resumeDto, int userId) throws UserNotFoundException {
        log.info("Creating resume for user ID: {}", userId);
        Optional<User> user = userDao.getUserById(userId);

        if (userId > userDao.getUsers().size() || userId < 1) {
            log.error("UserNotFoundException for creating resume, ID: {}", userId);
            throw new UserNotFoundException("Don't have access");
        }
        if (user.isPresent()) {
            if (user.get().getAccountType().name().equals("EMPLOYER")) {
                log.error("Access denied for employer to create resume, ID: {}", userId);
                throw new UserNotFoundException("Don't have access");
            }
        }

        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setUserId(resumeDto.getUserId());
        resume.setName(resumeDto.getName());
        resume.setCategoryId(resumeDto.getCategoryId());
        resume.setSalary(resumeDto.getSalary());
        resume.setActive(resumeDto.isActive());
        resume.setCreatedTime(resumeDto.getCreatedTime());
        resume.setUpdateTime(resumeDto.getUpdateTime());

        resumeDao.createResume(resume);
    }

    @Override
    public void editResume(ResumeDto resumeDto, int userId) throws UserNotFoundException {
        Optional<User> user = userDao.getUserById(userId);

        if (userId > userDao.getUsers().size() || userId < 1) {
            throw new UserNotFoundException("Don't have access");
        }
        if (user.isPresent()) {
            if (user.get().getAccountType().name().equals("EMPLOYER")) {
                throw new UserNotFoundException("Don't have access");
            }
        }

        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setName(resumeDto.getName());
        resume.setSalary(resumeDto.getSalary());
        resume.setActive(resumeDto.isActive());
        resume.setUpdateTime(resumeDto.getUpdateTime());

        resumeDao.editResume(resume);
    }

    @Override
    public void deleteResumeById(int id, int userId) throws UserNotFoundException {
        Optional<User> user = userDao.getUserById(userId);

        if (userId > userDao.getUsers().size() || userId < 1) {
            throw new UserNotFoundException("Don't have access");
        }
        if (user.isPresent()) {
            if (user.get().getAccountType().name().equals("EMPLOYER")) {
                throw new UserNotFoundException("Don't have access");
            }
        }

        resumeDao.deleteResumeById(id);
    }
}