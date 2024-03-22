package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.*;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.*;
import kg.attractor.headhunter.model.*;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private final CategoryDao categoryDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;
    private final EducationInfoDao educationInfoDao;
    private final ContactInfoDao contactInfoDao;
    private final ContactTypeDao contactTypeDao;

    @Override
    public void createResume(ResumeCreateDto resumeDto, int userId) throws UserNotFoundException,
            CategoryNotFoundException,
            ResumeNotFoundException,
            WorkExperienceNotFoundException,
            EducationInfoNotFoundException {
        Optional<User> user = userDao.getUserById(userId);
        if (user.get().getAccountType().equals(AccountType.EMPLOYER)) {
            throw new UserNotFoundException("You don't have access to add resume, because you are not applicant.");
        }
        if (userId != userDao.getUserByEmail(resumeDto.getAuthorEmail()).get().getId()) {
            throw new UserNotFoundException("You don't have access to add resume for this user.");
        }
        Integer categoryId = categoryDao.getCategoryById(resumeDto.getCategoryName()).getId();
        if (categoryId == null) {
            throw new CategoryNotFoundException("There isn't category like this " + resumeDto.getCategoryName());
        }
        if (resumeDto.getSalary().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ResumeNotFoundException("Salary cannot be negative.");
        }

        Resume resume = new Resume();
        resume.setAuthorId(userId);
        resume.setTitle(resumeDto.getTitle());
        resume.setCategoryId(categoryDao.getCategoryById(resumeDto.getCategoryName()).getId());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        Integer resumeId = resumeDao.createResumeAndReturnId(resume);

        for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {
            WorkExperienceInfoDto workExperienceInfoDto = resumeDto.getWorkExpInfos().get(i);
            WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
            workExperienceInfo.setResumeId(resume.getId());
            workExperienceInfo.setResumeId(resumeId);
            workExperienceInfo.setYears(workExperienceInfoDto.getYears());
            workExperienceInfo.setCompanyName(workExperienceInfoDto.getCompanyName());
            workExperienceInfo.setPosition(workExperienceInfoDto.getPosition());
            workExperienceInfo.setResponsibilities(workExperienceInfoDto.getResponsibilities());

            if (workExperienceInfoDto.getYears() < 0) {
                throw new WorkExperienceNotFoundException("Years of Work Experience cannot be negative");
            }

            workExperienceInfoDao.createWorkExperienceInfo(workExperienceInfo);
        }

        for (int i = 0; i < resumeDto.getEducationInfos().size(); i++) {
            EducationInfoDto educationInfoDto = resumeDto.getEducationInfos().get(i);
            EducationInfo educationInfo = new EducationInfo();
            educationInfo.setResumeId(resumeId);
            educationInfo.setInstitution(educationInfoDto.getInstitution());
            educationInfo.setProgram(educationInfoDto.getProgram());
            educationInfo.setStartDate(educationInfoDto.getStartDate());
            educationInfo.setEndDate(educationInfoDto.getEndDate());
            educationInfo.setDegree(educationInfoDto.getDegree());

            if (educationInfoDto.getStartDate().isAfter(educationInfoDto.getEndDate())) {
                throw new EducationInfoNotFoundException("Start date cannot be later then end date");
            }

            educationInfoDao.createEducationInfo(educationInfo);
        }

        for (int i = 0; i < resumeDto.getContactInfos().size(); i++) {
            ContactInfoDto contactInfoDto = resumeDto.getContactInfos().get(i);
            ContactInfo contactInfo = new ContactInfo();
            System.out.println(contactInfoDto);
            contactInfo.setResumeId(resumeId);
            contactInfo.setContactTypeId(contactTypeDao.getContactTypeIdByName(contactInfoDto.getContactType()).getId());
            contactInfo.setContent(contactInfoDto.getValue());

            contactInfoDao.createContactInfo(contactInfo);
        }
    }

    @Override
    public void editResume(ResumeEditDto resumeDto, int userId) throws UserNotFoundException, ResumeNotFoundException {
        Optional<User> user = userDao.getUserById(userId);

        if (user.get().getAccountType().equals(AccountType.EMPLOYER)) {
            throw new UserNotFoundException("You don't have access to add resume, because you are not applicant.");
        }
        if (userId != userDao.getUserByEmail(resumeDto.getEmail()).get().getId()) {
            throw new UserNotFoundException("You don't have access to add resume for this user.");
        }
        if (resumeDto.getSalary().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ResumeNotFoundException("Salary cannot be negative.");
        }

        Resume resume = new Resume();
        resume.setId(userId);
        resume.setTitle(resumeDto.getTitle());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resumeDao.editResume(resume);

        for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {
            WorkExperienceInfoEditDto workExperienceInfoEditDto = resumeDto.getWorkExpInfos().get(i);
            WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
            if (resumeDto.getWorkExpInfos().get(i).getId() == null) {
                Integer resumeId = resu;

                workExperienceInfo.setResumeId(resume.getId());
                workExperienceInfo.setResumeId(resumeId);
                workExperienceInfo.setYears(workExperienceInfoDto.getYears());
                workExperienceInfo.setCompanyName(workExperienceInfoDto.getCompanyName());
                workExperienceInfo.setPosition(workExperienceInfoDto.getPosition());
                workExperienceInfo.setResponsibilities(workExperienceInfoDto.getResponsibilities());

                workExperienceInfoDao.createWorkExperienceInfo();
            }
        }
    }

//
//    @Override
//    public List<ResumeDto> getAllResumes(int userId) throws UserNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("APPLICANT")) {
//                log.error("Access denied for applicant with ID: {}", userId);
//
//                throw new UserNotFoundException("Don't have access");
//            }
//        }
//
//        List<Resume> resumes = resumeDao.getResumes();
//        List<ResumeDto> dtos = new ArrayList<>();
//        resumes.forEach(resume -> dtos.add(ResumeDto.builder()
//                .authorEmail(userDao.getUserById(resume.getId()).get().getEmail())
//                .title(resume.getTitle())
//                .categoryName()
//        return dtos;
//    }
//
//    @Override
//    public List<ResumeDto> getResumesByTitle(String title, int userId) throws ResumeNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("APPLICANT")) {
//                log.error("Access denied for applicant with ID: {}", userId);
//
//                throw new ResumeNotFoundException("Don't have access");
//            }
//        }
//
//        List<Resume> resumes = resumeDao.getResumesByTitle(title);
//        if (resumes.isEmpty()) {
//            throw new ResumeNotFoundException("Can't find resumes with title: " + title);
//        }
//        return resumes.stream().map(resume -> ResumeDto.builder()
//                .id(resume.getId())
//                .userId(resume.getUserId())
//                .name(resume.getName())
//                .categoryId(resume.getCategoryId())
//                .salary(resume.getSalary())
//                .isActive(resume.isActive())
//                .createdTime(resume.getCreatedTime())
//                .updateTime(resume.getUpdateTime())
//                .build()).collect(Collectors.toList());
//    }
//
//
//    @Override
//    public List<ResumeDto> getResumesByCategoryId(int categoryId) throws ResumeNotFoundException {
//        List<Resume> resumes = resumeDao.getResumesByCategoryId(categoryId);
//        if (resumes.isEmpty() || categoryId == 0) {
//            throw new ResumeNotFoundException("Can't find resume with this category: " + categoryId);
//        }
//
//        return resumes.stream().map(resume -> ResumeDto.builder()
//                .id(resume.getId())
//                .userId(resume.getUserId())
//                .name(resume.getName())
//                .categoryId(resume.getCategoryId())
//                .salary(resume.getSalary())
//                .isActive(resume.isActive())
//                .createdTime(resume.getCreatedTime())
//                .updateTime(resume.getUpdateTime())
//                .build()).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ResumeDto> getResumesByCategoryName(String categoryName, int userId) throws ResumeNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("APPLICANT")) {
//                throw new ResumeNotFoundException("Don't have access");
//            }
//        }
//
//        List<Resume> resumes = resumeDao.getResumesByCategoryName(categoryName);
//        if (resumes.isEmpty()) {
//            throw new ResumeNotFoundException("Can't find resume with this category: " + categoryName);
//        }
//
//        return resumes.stream().map(resume -> ResumeDto.builder()
//                .id(resume.getId())
//                .userId(resume.getUserId())
//                .name(resume.getName())
//                .categoryId(resume.getCategoryId())
//                .salary(resume.getSalary())
//                .isActive(resume.isActive())
//                .createdTime(resume.getCreatedTime())
//                .updateTime(resume.getUpdateTime())
//                .build()).collect(Collectors.toList());
//    }
//
//
//    @Override
//    public List<ResumeDto> getActiveResumes() {
//        List<Resume> resumes = resumeDao.getActiveResumes();
//        List<ResumeDto> dtos = new ArrayList<>();
//        resumes.forEach(e -> dtos.add(ResumeDto.builder()
//                .id(e.getId())
//                .userId(e.getUserId())
//                .name(e.getName())
//                .categoryId(e.getCategoryId())
//                .salary(e.getSalary())
//                .isActive(e.isActive())
//                .createdTime(e.getCreatedTime())
//                .updateTime(e.getUpdateTime())
//                .build()));
//        return dtos;
//    }
//
//    @Override
//    public List<ResumeDto> getActiveResumesByUserId(int userId) throws ResumeNotFoundException {
//        List<Resume> resumes = resumeDao.getActiveResumesByUserId(userId);
//        if (resumes.isEmpty() || userId == 0) {
//            throw new ResumeNotFoundException("Can't find resume with this userId: " + userId);
//        }
//
//        return resumes.stream().map(resume -> ResumeDto.builder()
//                .id(resume.getId())
//                .userId(resume.getUserId())
//                .name(resume.getName())
//                .categoryId(resume.getCategoryId())
//                .salary(resume.getSalary())
//                .isActive(resume.isActive())
//                .createdTime(resume.getCreatedTime())
//                .updateTime(resume.getUpdateTime())
//                .build()).collect(Collectors.toList());
//    }
//
//
//    @Override
//    public List<ResumeDto> getResumesByUserId(int userId) throws ResumeNotFoundException {
//        List<Resume> resumes = resumeDao.getResumesByUserId(userId);
//        if (resumes.isEmpty() || userId == 0) {
//            throw new ResumeNotFoundException("Can't find resume with this category: " + userId);
//        }
//
//        return resumes.stream().map(resume -> ResumeDto.builder()
//                .id(resume.getId())
//                .userId(resume.getUserId())
//                .name(resume.getName())
//                .categoryId(resume.getCategoryId())
//                .salary(resume.getSalary())
//                .isActive(resume.isActive())
//                .createdTime(resume.getCreatedTime())
//                .updateTime(resume.getUpdateTime())
//                .build()).collect(Collectors.toList());
//    }
//
//    @Override
//    public ResumeDto getResumeById(int id) throws ResumeNotFoundException {
//        Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with id: " + id));
//        return ResumeDto.builder()
//                .id(resume.getId())
//                .userId(resume.getUserId())
//                .name(resume.getName())
//                .categoryId(resume.getCategoryId())
//                .salary(resume.getSalary())
//                .isActive(resume.isActive())
//                .createdTime(resume.getCreatedTime())
//                .updateTime(resume.getUpdateTime())
//                .build();
//    }
//
//

//    @Override
//    public void deleteResumeById(int id, int userId) throws UserNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("EMPLOYER")) {
//                throw new UserNotFoundException("Don't have access");
//            }
//        }
//
//        resumeDao.deleteResumeById(id);
//    }
}