package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.CategoryNotFoundException;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.*;
import kg.attractor.headhunter.repository.*;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ModelMapper modelMapper;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final EducationInfoRepository educationInfoRepository;
    private final ContactInfoRepository contactInfoRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    @SneakyThrows
    public void updateResumeTime(Integer resumeId, Authentication authentication) {
        LocalDateTime now = LocalDateTime.now();
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with id: " + resumeId));
        resume.setUpdateTime(now);
        resumeRepository.save(resume);
    }

    @Override
    @SneakyThrows
    public ResumeDto getResumeById(Integer resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this id"));

        User userEntity = userRepository.findById(resume.getAuthor().getId())
                .orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

        UserResumePrintDto userDto = UserResumePrintDto.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .age(userEntity.getAge())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .avatar(userEntity.getAvatar())
                .build();

        Integer id = resume.getId();
        String name = resume.getName();
        String categoryName = resume.getCategory().getName();
        BigDecimal salary = resume.getSalary();
        Boolean isActive = resume.getIsActive();

        List<WorkExperienceInfo> workExpInfos = workExperienceInfoRepository.findByResumeId(resume.getId());
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

        List<EducationInfo> educationInfos = educationInfoRepository.findByResumeId(resume.getId());
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

        List<ContactInfo> contactInfos = contactInfoRepository.findByResumeId(resume.getId());
        List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
        for (ContactInfo contactInfo : contactInfos) {
            ContactType contactType = contactTypeRepository.findById(contactInfo.getContactType().getId())
                    .orElseThrow(() -> new UserNotFoundException("Cannot find contact type"));
            ContactInfoDto contactInfoDto = modelMapper.map(contactInfo, ContactInfoDto.class);
            contactInfoDto.setContactType(contactType.getType());
            contactInfoDto.setValue(contactInfo.getContent());
            contactInfoDtoFormat.add(contactInfoDto);
            System.out.println(contactInfoDtoFormat);
        }

        return ResumeDto.builder()
                .id(id)
                .user(userDto)
                .name(name)
                .categoryName(categoryName)
                .salary(salary)
                .workExpInfos(workExperienceInfoDtoFormat)
                .educationInfos(educationInfoDtoFormat)
                .contactInfos(contactInfoDtoFormat)
                .isActive(isActive)
                .build();
    }


    @Override
    @SneakyThrows
    public Page<ResumeViewAllDto> getAllActiveResumes(int pageNumber, int pageSize, String category) {
        if (!category.equalsIgnoreCase("default")) {
            Category categoryFromDB = categoryRepository.findByName(category)
                    .orElseThrow(() -> new CategoryNotFoundException("Cannot find any resume with category: " + category));
            List<Resume> resumes = resumeRepository.findByCategory(categoryFromDB);

            List<ResumeViewAllDto> resumesDto = new ArrayList<>();

            for (Resume resume : resumes) {
                User userEntity = userRepository.findById(resume.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

                UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

                Integer id = resume.getId();
                String name = resume.getName();
                String categoryName = resume.getCategory().getName();
                BigDecimal salary = resume.getSalary();
                Boolean isActive = resume.getIsActive();
                LocalDateTime updateTime = resume.getUpdateTime();
                String formattedUpdateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(updateTime);

                //-----------------------------------------------------------------------------------------------------------------------------------------------------------

                List<WorkExperienceInfo> workExpInfos = workExperienceInfoRepository.findByResumeId(resume.getId());
                List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

                for (WorkExperienceInfo workExpInfo : workExpInfos) {
                    WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                    workExperienceInfoDtoFormat.add(workExperienceInfoDto);
                }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

                List<EducationInfo> educationInfos = educationInfoRepository.findByResumeId(resume.getId());
                List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

                for (EducationInfo educationInfo : educationInfos) {
                    EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                    educationInfoDtoFormat.add(educationInfoDto);
                }

                //----------------------------------------------------------------------------------------------------------------------------------------------

                List<ContactInfo> contactInfos = contactInfoRepository.findByResumeId(resume.getId());
                List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
                for (ContactInfo contactInfo : contactInfos) {
                    ContactType contactType = contactInfo.getContactType();
                    ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                    contactInfoDtoFormat.add(contactInfoDto);
                }


                ResumeViewAllDto resumeDto = ResumeViewAllDto.builder().id(id).user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).updateTime(formattedUpdateTime).build();

                resumesDto.add(resumeDto);
            }

            return toPage(resumesDto, PageRequest.of(pageNumber, pageSize));
        }

        List<Resume> resumes = resumeRepository.findAll();
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getIsActive().equals(false)) {
                resumes.remove(resumes.get(i));
            }
        }

        List<ResumeViewAllDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userRepository.findById(resume.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = resume.getId();
            String name = resume.getName();
            String categoryName = resume.getCategory().getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();
            LocalDateTime updateTime = resume.getUpdateTime();
            String formattedUpdateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(updateTime);

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoRepository.findByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoRepository.findByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoRepository.findByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactInfo.getContactType();
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeViewAllDto resumeDto = ResumeViewAllDto.builder().id(id)
                    .user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).updateTime(formattedUpdateTime).build();
            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("Cannot find any resume!");
        }

        return toPage(resumesDto, PageRequest.of(pageNumber, pageSize));
    }


    private Page<ResumeViewAllDto> toPage(List<ResumeViewAllDto> resumes, Pageable pageable) {
        if (pageable.getOffset() >= resumes.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize() > resumes.size() ?
                resumes.size() : pageable.getOffset() + pageable.getPageSize()));
        List<ResumeViewAllDto> subList = resumes.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, resumes.size());
    }

//    @Override
//    @SneakyThrows
//    public List<ResumeDto> getAllResumesByCategoryName(String categoriesName, Authentication authentication) {
//        Category category = categoryDao.getCategoryByName(categoriesName).orElseThrow(() -> new CategoryNotFoundException("Cannot find any resume with category: " + categoriesName));
//
//        List<Resume> resumes = resumeDao.getAllResumesByCategoryId(category.getId());
//
//        List<ResumeDto> resumesDto = new ArrayList<>();
//
//        for (Resume resume : resumes) {
//            User userEntity = userDao.getUserById(resume.getUserId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));
//
//            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();
//
//            String name = resume.getName();
//            String categoryName = categoryDao.getCategoryById(resume.getCategoryId()).getName();
//            BigDecimal salary = resume.getSalary();
//            Boolean isActive = resume.getIsActive();
//
//            //-----------------------------------------------------------------------------------------------------------------------------------------------------------
//
//            List<WorkExperienceInfo> workExpInfos = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId());
//            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();
//
//            for (WorkExperienceInfo workExpInfo : workExpInfos) {
//                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
//                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
//            }
//
////-----------------------------------------------------------------------------------------------------------------------------------------------------------
//
//            List<EducationInfo> educationInfos = educationInfoDao.getEducationInfoByResumeId(resume.getId());
//            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();
//
//            for (EducationInfo educationInfo : educationInfos) {
//                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
//                educationInfoDtoFormat.add(educationInfoDto);
//            }
//
//            //----------------------------------------------------------------------------------------------------------------------------------------------
//
//            List<ContactInfo> contactInfos = contactInfoDao.getContactInfoByResumeId(resume.getId());
//            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
//            for (ContactInfo contactInfo : contactInfos) {
//                ContactType contactType = contactTypeDao.getContactTypeById(contactInfo.getContactTypeId());
//                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
//                contactInfoDtoFormat.add(contactInfoDto);
//            }
//
//
//            ResumeDto resumeDto = ResumeDto.builder().user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();
//
//            resumesDto.add(resumeDto);
//        }
//        if (resumesDto.isEmpty()) {
//            throw new ResumeNotFoundException("Cannot find any resume with category:  " + category);
//        }
//
//        return resumesDto;
//    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user"));
    }

    @Override
    @SneakyThrows
    public List<ResumeDto> getAllResumesOfApplicant(Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());

        List<Resume> resumes = resumeRepository.findByAuthorId(user.getId());
        List<ResumeDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userRepository.findById(resume.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = resume.getId();
            String name = resume.getName();
            String categoryName = resume.getCategory().getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoRepository.findByResumeId(resume.getId());
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

            List<EducationInfo> educationInfos = educationInfoRepository.findByResumeId(resume.getId());
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

            List<ContactInfo> contactInfos = contactInfoRepository.findByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactTypeRepository.findById(contactInfo.getContactType().getId())
                        .orElseThrow(() -> new CategoryNotFoundException("Cannot find contact type"));
                ContactInfoDto contactInfoDto = ContactInfoDto.builder()
                        .contactType(contactType.getType())
                        .value(contactInfo.getContent())
                        .build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeDto resumeDto = ResumeDto.builder().id(id).user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).build();

            resumesDto.add(resumeDto);
        }
        if (resumesDto.isEmpty()) {
            throw new ResumeNotFoundException("You don't have any resumes");
        }

        return resumesDto;
    }

    @SneakyThrows
    @Override
    public List<ResumeViewAllDto> getAllResumesOfApplicantById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Can't find user by id: " + userId));

        List<Resume> resumes = resumeRepository.findByAuthorId(user.getId());

        List<ResumeViewAllDto> resumesDto = new ArrayList<>();

        for (Resume resume : resumes) {
            User userEntity = userRepository.findById(resume.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserResumePrintDto userDto = UserResumePrintDto.builder().name(userEntity.getName()).surname(userEntity.getSurname()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = resume.getId();
            String name = resume.getName();
            String categoryName = resume.getCategory().getName();
            BigDecimal salary = resume.getSalary();
            Boolean isActive = resume.getIsActive();
            LocalDateTime updateTime = resume.getUpdateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String formattedUpdateTime = updateTime.format(formatter);

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<WorkExperienceInfo> workExpInfos = workExperienceInfoRepository.findByResumeId(resume.getId());
            List<WorkExperienceInfoDto> workExperienceInfoDtoFormat = new ArrayList<>();

            for (WorkExperienceInfo workExpInfo : workExpInfos) {
                WorkExperienceInfoDto workExperienceInfoDto = WorkExperienceInfoDto.builder().years(workExpInfo.getYears()).companyName(workExpInfo.getCompanyName()).position(workExpInfo.getPosition()).responsibilities(workExpInfo.getResponsibilities()).build();
                workExperienceInfoDtoFormat.add(workExperienceInfoDto);
            }

            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            List<EducationInfo> educationInfos = educationInfoRepository.findByResumeId(resume.getId());
            List<EducationInfoDto> educationInfoDtoFormat = new ArrayList<>();

            for (EducationInfo educationInfo : educationInfos) {
                EducationInfoDto educationInfoDto = EducationInfoDto.builder().institution(educationInfo.getInstitution()).program(educationInfo.getProgram()).startDate(educationInfo.getStartDate()).endDate(educationInfo.getEndDate()).degree(educationInfo.getDegree()).build();
                educationInfoDtoFormat.add(educationInfoDto);
            }

            //----------------------------------------------------------------------------------------------------------------------------------------------

            List<ContactInfo> contactInfos = contactInfoRepository.findByResumeId(resume.getId());
            List<ContactInfoDto> contactInfoDtoFormat = new ArrayList<>();
            for (ContactInfo contactInfo : contactInfos) {
                ContactType contactType = contactInfo.getContactType();
                ContactInfoDto contactInfoDto = ContactInfoDto.builder().contactType(contactType.getType()).value(contactInfo.getContent()).build();
                contactInfoDtoFormat.add(contactInfoDto);
            }


            ResumeViewAllDto resumeDto = ResumeViewAllDto.builder().id(id).user(userDto).name(name).categoryName(categoryName).salary(salary).workExpInfos(workExperienceInfoDtoFormat).educationInfos(educationInfoDtoFormat).contactInfos(contactInfoDtoFormat).isActive(isActive).updateTime(formattedUpdateTime).build();

            resumesDto.add(resumeDto);
        }
        return resumesDto;
    }

    @Override
    @SneakyThrows
    public void createResumeForApplicant(ResumeCreateDto resumeDto, Authentication authentication) {
        System.out.println(resumeDto.getWorkExpInfos());
        System.out.println("test");
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        Category category = categoryRepository.findByName(resumeDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));

        Resume resume = new Resume();
        resume.setAuthor(user);
        resume.setName(resumeDto.getName());
        resume.setCategory(category);
        resume.setSalary(resumeDto.getSalary());
        resume.setCreatedTime(LocalDateTime.now());
        resume.setUpdateTime(LocalDateTime.now());

        if (Boolean.TRUE.equals(resumeDto.getIsActive())) {
            resume.setIsActive(true);
        } else {
            resume.setIsActive(false);
        }

        resumeRepository.save(resume);

        if (resumeDto.getWorkExpInfos() != null && !resumeDto.getWorkExpInfos().isEmpty()) {
            for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {
                WorkExperienceInfoDto workExperienceInfoDto = resumeDto.getWorkExpInfos().get(i);
                WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();

                workExperienceInfo.setResume(resume);
                workExperienceInfo.setYears(workExperienceInfoDto.getYears());
                workExperienceInfo.setCompanyName(workExperienceInfoDto.getCompanyName());
                workExperienceInfo.setPosition(workExperienceInfoDto.getPosition());
                workExperienceInfo.setResponsibilities(workExperienceInfoDto.getResponsibilities());

                workExperienceInfoRepository.save(workExperienceInfo);
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

                educationInfo.setResume(resume);
                educationInfo.setInstitution(educationInfoDto.getInstitution());
                educationInfo.setProgram(educationInfoDto.getProgram());
                educationInfo.setStartDate(educationInfoDto.getStartDate());
                educationInfo.setEndDate(educationInfoDto.getEndDate());
                educationInfo.setDegree(educationInfoDto.getDegree());

                educationInfoRepository.save(educationInfo);
            }
        }
        for (int i = 0; i < resumeDto.getContactInfos().size(); i++) {
            ContactInfoDto contactInfoDto = resumeDto.getContactInfos().get(i);
            ContactInfo contactInfo = new ContactInfo();

            contactInfo.setResume(resume);
            ContactType contactType = contactTypeRepository.findByType(contactInfoDto.getContactType());
            contactInfo.setContactType(contactType);
            contactInfo.setContent(contactInfoDto.getValue());

            contactInfoRepository.save(contactInfo);
        }
    }


//    @Override
//    @SneakyThrows
//    public void editResumeForApplicant(ResumeEditDto resumeDto, Authentication authentication, Integer id) {
//        User user = getUserFromAuth(authentication.getPrincipal().toString());
//        Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this id"));
//
//        if (!resumeDao.isApplicantHasResumeById(user, id)) {
//            throw new ResumeNotFoundException("Can't find your resume with this id");
//        }
//
//        resume.setId(id);
//        if (resumeDto.getName() != null) {
//            resume.setName(resumeDto.getName());
//        }
//
//        Category category;
//        if (resumeDto.getCategoryName() != null) {
//            category = categoryDao.getCategoryByName(resumeDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));
//            resume.setCategoryId(category.getId());
//        }
//
//        if (resumeDto.getSalary() != null) {
//            resume.setSalary(resumeDto.getSalary());
//        }
//
//        if (resumeDto.getIsActive() != null) {
//            resume.setIsActive(resumeDto.getIsActive());
//        }
//
//        resumeDao.editResume(resume);
//
//        if (resumeDto.getWorkExpInfos() != null && !resumeDto.getWorkExpInfos().isEmpty()) {
//            for (int i = 0; i < resumeDto.getWorkExpInfos().size(); i++) {
//
//                if (resumeDto.getWorkExpInfos().get(i).getId() == null || resumeDto.getWorkExpInfos().get(i).getId() == 0) {
//                    WorkExperienceInfoEditDto workExperienceInfoEditDto = resumeDto.getWorkExpInfos().get(i);
//                    if (workExperienceInfoEditDto.getYears() != null && workExperienceInfoEditDto.getCompanyName() != null && workExperienceInfoEditDto.getPosition() != null && workExperienceInfoEditDto.getResponsibilities() != null) {
//                        WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
//                        workExperienceInfo.setResumeId(id);
//                        workExperienceInfo.setYears(workExperienceInfoEditDto.getYears());
//                        workExperienceInfo.setCompanyName(workExperienceInfoEditDto.getCompanyName());
//                        workExperienceInfo.setPosition(workExperienceInfoEditDto.getPosition());
//                        workExperienceInfo.setResponsibilities(workExperienceInfoEditDto.getResponsibilities());
//
//                        workExperienceInfoDao.createWorkExperienceInfo(workExperienceInfo);
//                    } else {
//                        throw new WorkExperienceNotFoundException("Can't find one of fields to create workExpInfo!");
//                    }
//                } else {
//                    WorkExperienceInfoEditDto workExperienceInfoEditDto = resumeDto.getWorkExpInfos().get(i);
//                    WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
//
//                    workExperienceInfo.setId(workExperienceInfoEditDto.getId());
//                    workExperienceInfo.setResumeId(resume.getId());
//                    workExperienceInfo.setYears(workExperienceInfoEditDto.getYears());
//                    workExperienceInfo.setCompanyName(workExperienceInfoEditDto.getCompanyName());
//                    workExperienceInfo.setPosition(workExperienceInfoEditDto.getPosition());
//                    workExperienceInfo.setResponsibilities(workExperienceInfoEditDto.getResponsibilities());
//
//                    workExperienceInfoDao.editWorkExperienceInfo(workExperienceInfo);
//                }
//            }
//        }
//
//        if (resumeDto.getEducationInfos() != null && !resumeDto.getEducationInfos().isEmpty()) {
//            for (int i = 0; i < resumeDto.getEducationInfos().size(); i++) {
//
//                if (resumeDto.getEducationInfos().get(i).getId() == null || resumeDto.getEducationInfos().get(i).getId() == 0) {
//                    EducationInfoEditDto educationInfoEditDto = resumeDto.getEducationInfos().get(i);
//                    if (educationInfoEditDto.getInstitution() != null && !educationInfoEditDto.getInstitution().trim().isEmpty() && educationInfoEditDto.getProgram() != null && !educationInfoEditDto.getProgram().trim().isEmpty() && educationInfoEditDto.getStartDate() != null && educationInfoEditDto.getEndDate() != null && educationInfoEditDto.getDegree() != null) {
//                        EducationInfo educationInfo = new EducationInfo();
//
//                        educationInfo.setResumeId(id);
//                        educationInfo.setInstitution(educationInfoEditDto.getInstitution());
//                        educationInfo.setProgram(educationInfoEditDto.getProgram());
//                        educationInfo.setStartDate(educationInfoEditDto.getStartDate());
//                        educationInfo.setEndDate(educationInfoEditDto.getEndDate());
//                        educationInfo.setDegree(educationInfoEditDto.getDegree());
//                        educationInfoDao.createEducationInfo(educationInfo);
//                    } else {
//                        throw new EducationInfoNotFoundException("Can't find one of fields to create educationInfo!");
//                    }
//                } else {
//                    EducationInfoEditDto educationInfoEditDto = resumeDto.getEducationInfos().get(i);
//                    EducationInfo educationInfo = new EducationInfo();
//
//                    educationInfo.setId(educationInfoEditDto.getId());
//                    educationInfo.setResumeId(resume.getId());
//                    educationInfo.setInstitution(educationInfoEditDto.getInstitution());
//                    educationInfo.setProgram(educationInfoEditDto.getProgram());
//                    educationInfo.setStartDate(educationInfoEditDto.getStartDate());
//                    educationInfo.setEndDate(educationInfoEditDto.getEndDate());
//                    educationInfo.setDegree(educationInfoEditDto.getDegree());
//
//                    educationInfoDao.editEducationInfo(educationInfo);
//                }
//            }
//        }
//
//        if (resumeDto.getContactInfos() != null && !resumeDto.getContactInfos().isEmpty()) {
//            for (ContactInfoEditDto contactInfoDto : resumeDto.getContactInfos()) {
//                if (contactInfoDto.getId() == null || contactInfoDto.getId() == 0) {
//                    ContactInfo contactInfo = new ContactInfo();
//                    if (contactInfoDto.getContactType() != null && contactInfoDto.getValue() != null) {
//                        contactInfo.setResumeId(id);
//                        contactInfo.setContactTypeId(contactTypeDao.getContactTypeIdByName(contactInfoDto.getContactType()).getId());
//                        contactInfo.setContent(contactInfoDto.getValue());
//                        contactInfoDao.createContactInfo(contactInfo);
//                    } else {
//                        throw new ContactInfoNotFound("Can't find one of fields to create contact infos!");
//                    }
//                } else {
//                    ContactInfo contactInfo = new ContactInfo();
//                    contactInfo.setId(contactInfoDto.getId());
//                    contactInfo.setResumeId(id);
//                    contactInfo.setContactTypeId(contactTypeDao.getContactTypeIdByName(contactInfoDto.getContactType()).getId());
//                    contactInfo.setContent(contactInfoDto.getValue());
//                    contactInfoDao.editContactInfo(contactInfo);
//                }
//            }
//        }
//    }

    @Override
    @SneakyThrows
    public void deleteResumeById(Integer resumeId, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResumeNotFoundException("Can't find resume with this id"));
        if (!resume.getAuthor().getId().equals(user.getId())) {
            throw new ResumeNotFoundException("Can't find your resume with this id");
        }

        resumeRepository.deleteById(resumeId);
    }
}