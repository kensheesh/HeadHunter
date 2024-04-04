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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder().user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();

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

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder().user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();

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

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder().user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();

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

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder().user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();

            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("You don't have any resumes");
        }

        return resumesDto;
    }

    @SneakyThrows
    @Override
    public List<ResumeDto> getAllResumesOfApplicantById(Integer userId) {
        User user = userDao.getUserById(userId).orElseThrow(() -> new UserNotFoundException("can't find"));

        List<Resume> resumes = resumeDao.getAllResumesOfApplicant(user.getId());

        List<ResumeDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userDao.getUserById(resume.getUserId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = resume.getName();
            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder().user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();

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
                if (educationInfoDto.getStartDate() != null && educationInfoDto.getEndDate() != null && educationInfoDto.getEndDate().isBefore(educationInfoDto.getStartDate())) {
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
    public void editResumeForApplicant(ResumeEditDto resumeDto, Authentication authentication, Integer id) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this id"));

        if (!resumeDao.isApplicantHasResumeById(user, id)) {
            throw new ResumeNotFoundException("Can't find your resume with this id");
        }

        resume.setId(id);
        if (resumeDto.getName() != null) {
            resume.setName(resumeDto.getName());
        }

        Category category;
        if (resumeDto.getCategoryName() != null) {
            category = categoryDao.getCategoryByName(resumeDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));
            resume.setCategoryId(category.getId());
        }

        if (resumeDto.getSalary() != null) {
            resume.setSalary(resumeDto.getSalary());
        }

        if (resumeDto.getIsActive() != null) {
            resume.setIsActive(resumeDto.getIsActive());
        }

        resumeDao.editResume(resume);

        if (resumeDto.getWorkExpInfos() != null && !resumeDto.getWorkExpInfos().isEmpty()) {
            for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {

                if (resumeDto.getWorkExpInfos().get(i).getId() == null || resumeDto.getWorkExpInfos().get(i).getId() == 0) {
                    WorkExperienceInfoEditDto workExperienceInfoEditDto = resumeDto.getWorkExpInfos().get(i);
                    if (workExperienceInfoEditDto.getYears() != null && workExperienceInfoEditDto.getCompanyName() != null && workExperienceInfoEditDto.getPosition() != null && workExperienceInfoEditDto.getResponsibilities() != null) {
                        WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
                        workExperienceInfo.setResumeId(id);
                        workExperienceInfo.setYears(workExperienceInfoEditDto.getYears());
                        workExperienceInfo.setCompanyName(workExperienceInfoEditDto.getCompanyName());
                        workExperienceInfo.setPosition(workExperienceInfoEditDto.getPosition());
                        workExperienceInfo.setResponsibilities(workExperienceInfoEditDto.getResponsibilities());

                        workExperienceInfoDao.createWorkExperienceInfo(workExperienceInfo);
                    } else {
                        throw new WorkExperienceNotFoundException("Can't find one of fields to create workExpInfo!");
                    }
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
                    if (educationInfoEditDto.getInstitution() != null && !educationInfoEditDto.getInstitution().trim().isEmpty() && educationInfoEditDto.getProgram() != null && !educationInfoEditDto.getProgram().trim().isEmpty() && educationInfoEditDto.getStartDate() != null && educationInfoEditDto.getEndDate() != null && educationInfoEditDto.getDegree() != null) {
                        EducationInfo educationInfo = new EducationInfo();

                        educationInfo.setResumeId(id);
                        educationInfo.setInstitution(educationInfoEditDto.getInstitution());
                        educationInfo.setProgram(educationInfoEditDto.getProgram());
                        educationInfo.setStartDate(educationInfoEditDto.getStartDate());
                        educationInfo.setEndDate(educationInfoEditDto.getEndDate());
                        educationInfo.setDegree(educationInfoEditDto.getDegree());
                        educationInfoDao.createEducationInfo(educationInfo);
                    } else {
                        throw new EducationInfoNotFoundException("Can't find one of fields to create educationInfo!");
                    }
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

        if (resumeDto.getContactInfos() != null && !resumeDto.getContactInfos().isEmpty()) {
            for (ContactInfoEditDto contactInfoDto : resumeDto.getContactInfos()) {
                if (contactInfoDto.getId() == null || contactInfoDto.getId() == 0) {
                    ContactInfo contactInfo = new ContactInfo();
                    if (contactInfoDto.getContactType() != null && contactInfoDto.getValue() != null) {
                        contactInfo.setResumeId(id);
                        contactInfo.setContactTypeId(contactTypeDao.getContactTypeIdByName(contactInfoDto.getContactType()).getId());
                        contactInfo.setContent(contactInfoDto.getValue());
                        contactInfoDao.createContactInfo(contactInfo);
                    } else {
                        throw new ContactInfoNotFound("Can't find one of fields to create contact infos!");
                    }
                } else {
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setId(contactInfoDto.getId());
                    contactInfo.setResumeId(id);
                    contactInfo.setContactTypeId(contactTypeDao.getContactTypeIdByName(contactInfoDto.getContactType()).getId());
                    contactInfo.setContent(contactInfoDto.getValue());
                    contactInfoDao.editContactInfo(contactInfo);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void deleteResumeById(Integer resumeId, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        resumeDao.getResumeById(resumeId).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this id"));
        if (!resumeDao.isApplicantHasResumeById(user, resumeId)) {
            throw new ResumeNotFoundException("Can't find your resume with this id");
        }

        resumeDao.deleteApplicantsResumeById(resumeId);
    }
}