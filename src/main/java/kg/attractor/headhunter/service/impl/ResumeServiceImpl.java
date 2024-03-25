package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.*;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.CategoryNotFoundException;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.*;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<ResumeDto> getAllActiveResumes(Authentication authentication) {
        List<Resume> resumes = resumeDao.getAllActiveResumes();

        List<ResumeDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userDao.getUserById(resume.getUserId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder()
                    .name(userEntity.getName())
                    .surname(userEntity.getSurname())
                    .age(userEntity.getAge())
                    .email(userEntity.getEmail())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .avatar(userEntity.getAvatar())
                    .build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder()
                        .years(workExpInfo.getYears())
                        .companyName(workExpInfo.getCompanyName())
                        .position(workExpInfo.getPosition())
                        .responsibilities(workExpInfo.getResponsibilities())
                        .build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder()
                        .institution(educationInfo.getInstitution())
                        .program(educationInfo.getProgram())
                        .startDate(educationInfo.getStartDate())
                        .endDate(educationInfo.getEndDate())
                        .degree(educationInfo.getDegree())
                        .build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder()
                        .contactType(contactType.getType())
                        .value(contactInfo.getContent())
                        .build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder()
                    .user(userDto)
                    .name(name)
                    .categoryName(categoryName)
                    .salary(salary)
                    .workExpInfos(workExperienceInfoDtoFormat)
                    .educationInfos(educationInfoDtoFormat)
                    .contactInfos(contactInfoDtoFormat)
                    .isActive(isActive)
                    .build();

            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("Cannot find any resume!");
        }

        return resumesDto;
    }


    @Override
    @SneakyThrows
    public List<ResumeDto> getAllResumesByName(String title, Authentication authentication) {
        List<Resume> resumes = resumeDao.getAllResumesByName(title);

        List<ResumeDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userDao.getUserById(resume.getUserId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder()
                    .name(userEntity.getName())
                    .surname(userEntity.getSurname())
                    .age(userEntity.getAge())
                    .email(userEntity.getEmail())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .avatar(userEntity.getAvatar())
                    .build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder()
                        .years(workExpInfo.getYears())
                        .companyName(workExpInfo.getCompanyName())
                        .position(workExpInfo.getPosition())
                        .responsibilities(workExpInfo.getResponsibilities())
                        .build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder()
                        .institution(educationInfo.getInstitution())
                        .program(educationInfo.getProgram())
                        .startDate(educationInfo.getStartDate())
                        .endDate(educationInfo.getEndDate())
                        .degree(educationInfo.getDegree())
                        .build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder()
                        .contactType(contactType.getType())
                        .value(contactInfo.getContent())
                        .build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder()
                    .user(userDto)
                    .name(name)
                    .categoryName(categoryName)
                    .salary(salary)
                    .workExpInfos(workExperienceInfoDtoFormat)
                    .educationInfos(educationInfoDtoFormat)
                    .contactInfos(contactInfoDtoFormat)
                    .isActive(isActive)
                    .build();

            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("Cannot find any resume with name " + title);
        }

        return resumesDto;
    }

    @Override
    @SneakyThrows
    public List<ResumeDto> getAllResumesByCategoryName(String categoriesName, Authentication authentication) {
        Category category = categoryDao.getCategoryByName(categoriesName).orElseThrow(() -> new CategoryNotFoundException("Cannot find any resume with category: " + categoriesName));

        List<Resume> resumes = resumeDao.getAllResumesByCategoryId(category.getId());

        List<ResumeDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userDao.getUserById(resume.getUserId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder()
                    .name(userEntity.getName())
                    .surname(userEntity.getSurname())
                    .age(userEntity.getAge())
                    .email(userEntity.getEmail())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .avatar(userEntity.getAvatar())
                    .build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder()
                        .years(workExpInfo.getYears())
                        .companyName(workExpInfo.getCompanyName())
                        .position(workExpInfo.getPosition())
                        .responsibilities(workExpInfo.getResponsibilities())
                        .build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder()
                        .institution(educationInfo.getInstitution())
                        .program(educationInfo.getProgram())
                        .startDate(educationInfo.getStartDate())
                        .endDate(educationInfo.getEndDate())
                        .degree(educationInfo.getDegree())
                        .build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder()
                        .contactType(contactType.getType())
                        .value(contactInfo.getContent())
                        .build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder()
                    .user(userDto)
                    .name(name)
                    .categoryName(categoryName)
                    .salary(salary)
                    .workExpInfos(workExperienceInfoDtoFormat)
                    .educationInfos(educationInfoDtoFormat)
                    .contactInfos(contactInfoDtoFormat)
                    .isActive(isActive)
                    .build();

            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("Cannot find any resume with category:  " + category);
        }

        return resumesDto;
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user"));
    }

    @Override
    @SneakyThrows
    public List<ResumeDto> getAllResumesOfApplicant(Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());

        List<Resume> resumes = resumeDao.getAllResumesOfApplicant(user.getId());

        List<ResumeDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userDao.getUserById(resume.getUserId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder()
                    .name(userEntity.getName())
                    .surname(userEntity.getSurname())
                    .age(userEntity.getAge())
                    .email(userEntity.getEmail())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .avatar(userEntity.getAvatar())
                    .build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder()
                        .years(workExpInfo.getYears())
                        .companyName(workExpInfo.getCompanyName())
                        .position(workExpInfo.getPosition())
                        .responsibilities(workExpInfo.getResponsibilities())
                        .build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder()
                        .institution(educationInfo.getInstitution())
                        .program(educationInfo.getProgram())
                        .startDate(educationInfo.getStartDate())
                        .endDate(educationInfo.getEndDate())
                        .degree(educationInfo.getDegree())
                        .build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder()
                        .contactType(contactType.getType())
                        .value(contactInfo.getContent())
                        .build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder()
                    .user(userDto)
                    .name(name)
                    .categoryName(categoryName)
                    .salary(salary)
                    .workExpInfos(workExperienceInfoDtoFormat)
                    .educationInfos(educationInfoDtoFormat)
                    .contactInfos(contactInfoDtoFormat)
                    .isActive(isActive)
                    .build();

            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("You don't have any resumes");
        }

        return resumesDto;
    }

    @Override
    @SneakyThrows
    public void createResumeForApplicant(ResumeCreateDto resumeDto, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        Category category = categoryDao.getCategoryByName(resumeDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));

        Resume resume = new Resume();
        resume.setUserId(user.getId());
        resume.setName(resumeDto.getName());
        resume.setCategoryId(category.getId());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        Integer resumeId = resumeDao.createResumeAndReturnId(resume);

        if (resumeDto.getWorkExpInfos() != null && !resumeDto.getWorkExpInfos().isEmpty()) {
            for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {
                WorkExperienceInfoDto workExperienceInfoDto = resumeDto.getWorkExpInfos().get(i);
                WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();

                workExperienceInfo.setResumeId(resumeId);
                workExperienceInfo.setYears(workExperienceInfoDto.getYears());
                workExperienceInfo.setCompanyName(workExperienceInfoDto.getCompanyName());
                workExperienceInfo.setPosition(workExperienceInfoDto.getPosition());
                workExperienceInfo.setResponsibilities(workExperienceInfoDto.getResponsibilities());

                workExperienceInfoDao.createWorkExperienceInfo(workExperienceInfo);
            }
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------
        if (resumeDto.getEducationInfos() != null && !resumeDto.getEducationInfos().isEmpty()) {
            for (int i = 0; i < resumeDto.getEducationInfos().size(); i++) {
                EducationInfoDto educationInfoDto = resumeDto.getEducationInfos().get(i);
                EducationInfo educationInfo = new EducationInfo();
                if (educationInfoDto.getStartDate() != null && educationInfoDto.getEndDate() != null &&
                        educationInfoDto.getEndDate().isBefore(educationInfoDto.getStartDate())) {
                    throw new ResumeNotFoundException("End date cannot be before start date");
                }

                educationInfo.setResumeId(resumeId);
                educationInfo.setInstitution(educationInfoDto.getInstitution());
                educationInfo.setProgram(educationInfoDto.getProgram());
                educationInfo.setStartDate(educationInfoDto.getStartDate());
                educationInfo.setEndDate(educationInfoDto.getEndDate());
                educationInfo.setDegree(educationInfoDto.getDegree());

                educationInfoDao.createEducationInfo(educationInfo);
            }
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------
        for (int i = 0; i < resumeDto.getContactInfos().size(); i++) {
            ContactInfoDto contactInfoDto = resumeDto.getContactInfos().get(i);
            ContactInfo contactInfo = new ContactInfo();

            contactInfo.setResumeId(resumeId);
            contactInfo.setContactTypeId(contactTypeDao.getContactTypeIdByName(contactInfoDto.getContactType()).getId());
            contactInfo.setContent(contactInfoDto.getValue());

            contactInfoDao.createContactInfo(contactInfo);
        }
    }


    @Override
    @SneakyThrows
    public void editResumeForApplicant(ResumeEditDto resumeDto, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        if (resumeDao.isApplicantHasResumeById(user, resumeDto.getId())) {
            throw new ResumeNotFoundException("Can't find your resume with this id");
        }
        Category category = categoryDao.getCategoryByName(resumeDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));

        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setName(resumeDto.getName());
        resume.setCategoryId(category.getId());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resumeDao.editResume(resume);

        if (resumeDto.getWorkExpInfos() != null && !resumeDto.getWorkExpInfos().isEmpty()) {
            for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {

                if (resumeDto.getWorkExpInfos().get(i).getId() == null || resumeDto.getWorkExpInfos().get(i).getId() == 0) {
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

                    workExperienceInfo.setId(workExperienceInfoEditDto.getId());
                    workExperienceInfo.setResumeId(resume.getId());
                    workExperienceInfo.setYears(workExperienceInfoEditDto.getYears());
                    workExperienceInfo.setCompanyName(workExperienceInfoEditDto.getCompanyName());
                    workExperienceInfo.setPosition(workExperienceInfoEditDto.getPosition());
                    workExperienceInfo.setResponsibilities(workExperienceInfoEditDto.getResponsibilities());

                    workExperienceInfoDao.editWorkExperienceInfo(workExperienceInfo);
                }
            }
        }

        if (resumeDto.getEducationInfos() != null && !resumeDto.getEducationInfos().isEmpty()) {
            for (int i = 0; i < resumeDto.getEducationInfos().size(); i++) {

                if (resumeDto.getEducationInfos().get(i).getId() == null || resumeDto.getEducationInfos().get(i).getId() == 0) {
                    EducationInfoEditDto educationInfoEditDto = resumeDto.getEducationInfos().get(i);
                    EducationInfo educationInfo = new EducationInfo();

                    educationInfo.setResumeId(resumeDto.getId());
                    educationInfo.setInstitution(educationInfoEditDto.getInstitution());
                    educationInfo.setProgram(educationInfoEditDto.getProgram());
                    educationInfo.setStartDate(educationInfoEditDto.getStartDate());
                    educationInfo.setEndDate(educationInfoEditDto.getEndDate());
                    educationInfo.setDegree(educationInfoEditDto.getDegree());
                    educationInfoDao.createEducationInfo(educationInfo);
                } else {
                    EducationInfoEditDto educationInfoEditDto = resumeDto.getEducationInfos().get(i);
                    EducationInfo educationInfo = new EducationInfo();

                    educationInfo.setId(educationInfoEditDto.getId());
                    educationInfo.setResumeId(resume.getId());
                    educationInfo.setInstitution(educationInfoEditDto.getInstitution());
                    educationInfo.setProgram(educationInfoEditDto.getProgram());
                    educationInfo.setStartDate(educationInfoEditDto.getStartDate());
                    educationInfo.setEndDate(educationInfoEditDto.getEndDate());
                    educationInfo.setDegree(educationInfoEditDto.getDegree());

                    educationInfoDao.editEducationInfo(educationInfo);
                }
            }
        }
    }
//
//    @Override
//    @SneakyThrows
//    public void deleteResumeById(int id, int userId) {
//        if (!isEmployer(userId)) {
//            log.error("User not found");
//            throw new UserNotFoundException("Cannot find user");
//
//        }
//        resumeDao.deleteResumeById(id);
//    }
//
//    @Override
//    @SneakyThrows
//    public List<ResumeDto> getUsersAllResumes(int userId) {
//        if (isEmployer(userId)) {
//            log.error("User not found");
//            throw new UserNotFoundException("Employers doesnt have resumes");
//        }
//
//        User user = userDao.getUserById(userId).orElseThrow(() -> new UserNotFoundException("Cannot find user"));
//        List<Resume> resumes = resumeDao.getResumesByUserId(userId);
//
//        List<ResumeDto> resumeDtos = new ArrayList<>();
//        for (Resume resume : resumes) {
//            List<WorkExperienceInfo> workExperiences = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
//            List<WorkExperienceInfoDto> workExperienceInfoDtos = workExperiences.stream()
//                    .map(we -> new WorkExperienceInfoDto(we.getYears(), we.getCompanyName(), we.getPosition(), we.getResponsibilities()))
//                    .collect(Collectors.toList());
//
//            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
//            List<EducationInfoDto> educationInfoDtos = educationInfos.stream()
//                    .map(ei -> new EducationInfoDto(ei.getInstitution(), ei.getProgram(), ei.getStartDate(), ei.getEndDate(), ei.getDegree()))
//                    .collect(Collectors.toList());
//
//            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
//            for (ContactInfo contactInfo : contactInfos) {
//                System.out.println(contactInfo);
//            }
//            List<ContactInfoDto> contactInfoDtos = contactInfos.stream()
//                    .map(ci -> new ContactInfoDto(contactTypeDao.getContactTypeById(ci.getContactTypeId()).getType(), ci.getContent()))
//                    .collect(Collectors.toList());
//
//            ResumeDto resumeDto = ResumeDto.builder()
//                    .authorEmail(user.getEmail())
//                    .name(resume.getName())
//                    .categoryName(categoryDao.getCategoryById(resume.getCategoryId()).getName())
//                    .salary(resume.getSalary())
//                    .workExpInfos(workExperienceInfoDtos)
//                    .educationInfos(educationInfoDtos)
//                    .contactInfos(contactInfoDtos)
//                    .isActive(resume.getIsActive())
//                    .build();
//
//            resumeDtos.add(resumeDto);
//        }
//        return resumeDtos;
//    }
//
//    @Override
//    public List<ResumeDto> getUsersResumeByTitle(String name, int userId) {
//        List<Resume> resumes = resumeDao.getResumesByUserIdAndName(userId, name);
//
//        return resumes.stream()
//                .map(this::convertToResumeDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ResumeDto> getUsersResumesByCategoryName(String categoryName, int userId) {
//        Integer categoryId = categoryDao.getCategoryByName(categoryName).get().getId();
//        List<Resume> resumes = resumeDao.getResumesByUserIdAndCategoryName(userId, categoryId);
//
//        return resumes.stream()
//                .map(this::convertToResumeDto)
//                .collect(Collectors.toList());
//    }
//
//
//    @Override
//    public List<ResumeDto> getUsersActiveResumes(int userId) {
//        List<Resume> resumes = resumeDao.getActiveResumesByUserId(userId);
//
//        return resumes.stream()
//                .map(this::convertToResumeDto)
//                .collect(Collectors.toList());
//    }
//
//    private ResumeDto convertToResumeDto(Resume resume) {
//        Optional<User> user = userDao.getUserById(resume.getUserId());
//
//        List<WorkExperienceInfo> workExperiences = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
//        List<WorkExperienceInfoDto> workExperienceInfoDtos = workExperiences.stream()
//                .map(we -> new WorkExperienceInfoDto(we.getYears(), we.getCompanyName(), we.getPosition(), we.getResponsibilities()))
//                .collect(Collectors.toList());
//
//        List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
//        List<EducationInfoDto> educationInfoDtos = educationInfos.stream()
//                .map(ei -> new EducationInfoDto(ei.getInstitution(), ei.getProgram(), ei.getStartDate(), ei.getEndDate(), ei.getDegree()))
//                .collect(Collectors.toList());
//
//        List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
//        List<ContactInfoDto> contactInfoDtos = contactInfos.stream()
//                .map(ci -> new ContactInfoDto(contactTypeDao.getContactTypeById(ci.getContactTypeId()).getType(), ci.getContent()))
//                .collect(Collectors.toList());
//
//        Category category = categoryDao.getCategoryById(resume.getCategoryId());
//
//        return ResumeDto.builder()
//                .authorEmail(user.get().getEmail())
//                .name(resume.getName())
//                .categoryName(category.getName())
//                .salary(resume.getSalary())
//                .isActive(resume.getIsActive())
//                .workExpInfos(workExperienceInfoDtos)
//                .educationInfos(educationInfoDtos)
//                .contactInfos(contactInfoDtos)
//                .build();
//    }
//
//    @SneakyThrows
//    public boolean isEmployer(int id) {
//        boolean isEmployer = false;
//        Optional<User> optUser = userDao.getUserById(id);
//        if (optUser.isPresent()) {
//            UserDto dto = getUserById(id);
//            if (dto.getAccountType().name().equalsIgnoreCase(String.valueOf(AccountType.EMPLOYER))) {
//                isEmployer = true;
//                return isEmployer;
//            }
//        } else {
//            throw new UserNotFoundException("Employer cannot create resume");
//        }
//        return isEmployer;
//    }
//
//
//    @Override
//    public UserDto getUserById(int id) {
//        Optional<User> user = userDao.getUserById(id);
//        return modelMapper.map(user, UserDto.class);
//    }
}