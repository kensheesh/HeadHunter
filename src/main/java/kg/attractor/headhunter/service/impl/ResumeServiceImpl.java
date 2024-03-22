package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.*;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.*;
import kg.attractor.headhunter.model.*;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ModelMapper modelMapper;
    private final ResumeDao resumeDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;
    private final EducationInfoDao educationInfoDao;
    private final ContactInfoDao contactInfoDao;
    private final ContactTypeDao contactTypeDao;

    @Override
    @SneakyThrows
    public void createResume(ResumeCreateDto resumeDto, int userId) {

        System.out.println(resumeDto.getName());
        if (isEmployer(userId)) {
            log.error("User not found");
            throw new UserNotFoundException("Cannot find user");

        }

        if (userId != userDao.getUserByEmail(resumeDto.getAuthorEmail()).get().getId()) {
            throw new UserNotFoundException("You don't have access to add resume for this user.");
        }
        Integer categoryId = categoryDao.getCategoryByName(resumeDto.getCategoryName()).getId();
        if (categoryId == null) {
            throw new CategoryNotFoundException("There isn't category like this " + resumeDto.getCategoryName());
        }
        if (resumeDto.getSalary().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ResumeNotFoundException("Salary cannot be negative.");
        }

        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setName(resumeDto.getName());
        resume.setCategoryId(categoryDao.getCategoryByName(resumeDto.getCategoryName()).getId());
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

//            if (workExperienceInfoDto.getYears() < 0) {
//                throw new WorkExperienceNotFoundException("Years of Work Experience cannot be negative");
//            }

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
    @SneakyThrows
    public void editResume(ResumeEditDto resumeDto, int userId) {
        if (!isEmployer(userId)) {
            log.error("User not found");
            throw new UserNotFoundException("Cannot find user");
        }

        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setName(resumeDto.getName());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resumeDao.editResume(resume);

        for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {

            if (resumeDto.getWorkExpInfos().get(i).getId() == null) {
                WorkExperienceInfoEditDto workExperienceInfoEditDto = resumeDto.getWorkExpInfos().get(i);
                WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();

                workExperienceInfo.setResumeId(resumeDto.getId());
                workExperienceInfo.setYears(workExperienceInfoEditDto.getYears());
                workExperienceInfo.setCompanyName(workExperienceInfoEditDto.getCompanyName());
                workExperienceInfo.setPosition(workExperienceInfoEditDto.getPosition());
                workExperienceInfo.setResponsibilities(workExperienceInfoEditDto.getResponsibilities());

                workExperienceInfoDao.createWorkExperienceInfo(workExperienceInfo);
            } else {
                WorkExperienceInfoEditDto workExperienceInfoEditDto = resumeDto.getWorkExpInfos().get(i);
                WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();

                workExperienceInfo.setResumeId(resume.getId());
                workExperienceInfo.setYears(workExperienceInfoEditDto.getYears());
                workExperienceInfo.setCompanyName(workExperienceInfoEditDto.getCompanyName());
                workExperienceInfo.setPosition(workExperienceInfoEditDto.getPosition());
                workExperienceInfo.setResponsibilities(workExperienceInfoEditDto.getResponsibilities());

                workExperienceInfoDao.editWorkExperienceInfo(workExperienceInfo);
            }
            // Почти реализовал
        }
    }

    @Override
    @SneakyThrows
    public void deleteResumeById(int id, int userId) {
        if (!isEmployer(userId)) {
            log.error("User not found");
            throw new UserNotFoundException("Cannot find user");

        }
        resumeDao.deleteResumeById(id);
    }

    @Override
    @SneakyThrows
    public List<ResumeDto> getUsersAllResumes(int userId) {
        if (isEmployer(userId)) {
            log.error("User not found");
            throw new UserNotFoundException("Employers doesnt have resumes");
        }

        User user = userDao.getUserById(userId).orElseThrow(() -> new UserNotFoundException("Cannot find user"));
        List<Resume> resumes = resumeDao.getResumesByUserId(userId);

        List<ResumeDto> resumeDtos = new ArrayList<>();
        for (Resume resume : resumes) {
            List<WorkExperienceInfo> workExperiences = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtos = workExperiences.stream()
                    .map(we -> new WorkExperienceInfoDto(we.getYears(), we.getCompanyName(), we.getPosition(), we.getResponsibilities()))
                    .collect(Collectors.toList());

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtos = educationInfos.stream()
                    .map(ei -> new EducationInfoDto(ei.getInstitution(), ei.getProgram(), ei.getStartDate(), ei.getEndDate(), ei.getDegree()))
                    .collect(Collectors.toList());

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            for (ContactInfo contactInfo : contactInfos) {
                System.out.println(contactInfo);
            }
            List<ContactInfoDto> contactInfoDtos = contactInfos.stream()
                    .map(ci -> new ContactInfoDto(contactTypeDao.getContactTypeById(ci.getContactTypeId()).getType(), ci.getContent()))
                    .collect(Collectors.toList());

            ResumeDto resumeDto = ResumeDto.builder()
                    .authorEmail(user.getEmail())
                    .name(resume.getName())
                    .categoryName(categoryDao.getCategoryById(resume.getCategoryId()).getName())
                    .salary(resume.getSalary())
                    .workExpInfos(workExperienceInfoDtos)
                    .educationInfos(educationInfoDtos)
                    .contactInfos(contactInfoDtos)
                    .isActive(resume.getIsActive())
                    .build();

            resumeDtos.add(resumeDto);
        }
        return resumeDtos;
    }

    @Override
    public List<ResumeDto> getUsersResumeByTitle(String name, int userId) {
        List<Resume> resumes = resumeDao.getResumesByUserIdAndName(userId, name);

        return resumes.stream()
                .map(this::convertToResumeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getUsersResumesByCategoryName(String categoryName, int userId) {
        Integer categoryId = categoryDao.getCategoryByName(categoryName).getId();
        List<Resume> resumes = resumeDao.getResumesByUserIdAndCategoryName(userId, categoryId);

        return resumes.stream()
                .map(this::convertToResumeDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ResumeDto> getUsersActiveResumes(int userId) {
        List<Resume> resumes = resumeDao.getActiveResumesByUserId(userId);

        return resumes.stream()
                .map(this::convertToResumeDto)
                .collect(Collectors.toList());
    }

    private ResumeDto convertToResumeDto(Resume resume) {
        Optional<User> user = userDao.getUserById(resume.getUserId());

        List<WorkExperienceInfo> workExperiences = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
        List<WorkExperienceInfoDto> workExperienceInfoDtos = workExperiences.stream()
                .map(we -> new WorkExperienceInfoDto(we.getYears(), we.getCompanyName(), we.getPosition(), we.getResponsibilities()))
                .collect(Collectors.toList());

        List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
        List<EducationInfoDto> educationInfoDtos = educationInfos.stream()
                .map(ei -> new EducationInfoDto(ei.getInstitution(), ei.getProgram(), ei.getStartDate(), ei.getEndDate(), ei.getDegree()))
                .collect(Collectors.toList());

        List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
        List<ContactInfoDto> contactInfoDtos = contactInfos.stream()
                .map(ci -> new ContactInfoDto(contactTypeDao.getContactTypeById(ci.getContactTypeId()).getType(), ci.getContent()))
                .collect(Collectors.toList());

        Category category = categoryDao.getCategoryById(resume.getCategoryId());

        return ResumeDto.builder()
                .authorEmail(user.get().getEmail())
                .name(resume.getName())
                .categoryName(category.getName())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .workExpInfos(workExperienceInfoDtos)
                .educationInfos(educationInfoDtos)
                .contactInfos(contactInfoDtos)
                .build();
    }

    @SneakyThrows
    public boolean isEmployer(int id) {
        boolean isEmployer = false;
        Optional<User> optUser = userDao.getUserById(id);
        if (optUser.isPresent()) {
            UserDto dto = getUserById(id);
            if (dto.getAccountType().name().equalsIgnoreCase(String.valueOf(AccountType.EMPLOYER))) {
                isEmployer = true;
                return isEmployer;
            }
        } else {
            throw new UserNotFoundException("Cannot find user");
        }
        return isEmployer;
    }


    @Override
    public UserDto getUserById(int id) {
        Optional<User> user = userDao.getUserById(id);
        return modelMapper.map(user, UserDto.class);
    }
}