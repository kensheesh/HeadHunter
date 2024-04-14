package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.CategoryDao;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dao.VacancyDao;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.CategoryNotFoundException;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.model.Category;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.model.Vacancy;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final UserDao userDao;
    private final VacancyDao vacancyDao;
    private final CategoryDao categoryDao;

    @Override
    @SneakyThrows
    public VacancyDto getVacancyById(Integer id) {
        Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(() -> new VacancyNotFoundException("Can't find vacancy with id: " + id));
        User user = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("can't find user with this id"));
        UserForVacancyPrintDto userDto = new UserForVacancyPrintDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAvatar(user.getAvatar());
        return VacancyDto.builder().id(vacancy.getId()).name(vacancy.getName()).description(vacancy.getDescription()).categoryName(categoryDao.getCategoryById(vacancy.getCategoryId()).getName()).salary(vacancy.getSalary()).experienceFrom(vacancy.getExperienceFrom()).experienceTo(vacancy.getExperienceTo()).isActive(vacancy.getIsActive()).createdDate(vacancy.getCreatedDate()).updateTime(vacancy.getUpdateTime()).user(userDto).build();
    }


//    @Override
//    @SneakyThrows
//    public List<VacancyDto> getAllActiveVacancies() {
//
//        List<Vacancy> vacancies = vacancyDao.getAllActiveVacancies();
//
//        List<VacancyDto> vacancyDtoList = new ArrayList<>();
//
//        for (Vacancy vacancy : vacancies) {
//            User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));
//
//            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().id(userEntity.getId()).name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();
//
//            Integer id = vacancy.getId();
//            String name = vacancy.getName();
//            String description = vacancy.getDescription();
//            String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
//            BigDecimal salary = vacancy.getSalary();
//            Integer experienceFrom = vacancy.getExperienceFrom();
//            Integer experienceTo = vacancy.getExperienceTo();
//            LocalDateTime createdDate = vacancy.getCreatedDate();
//            LocalDateTime updateTime = vacancy.getUpdateTime();
//            Boolean isActive = vacancy.getIsActive();
//
//            VacancyDto vacancyDto = VacancyDto.builder().id(id).user(userDto).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(createdDate).updateTime(updateTime).isActive(isActive).build();
//            vacancyDtoList.add(vacancyDto);
//        }
//        return vacancyDtoList;
//    }

    @SneakyThrows
    public Page<VacancyViewAllDto> getAllActiveVacancies(int pageNumber, int pageSize, String category) {

        if (!category.equalsIgnoreCase("default")) {
            System.out.println(category);
            Category categoryFromDB = categoryDao.getCategoryByName(category).orElseThrow(() -> new CategoryNotFoundException("Cannot find any resume with category: " + category));

            List<Vacancy> vacancies = vacancyDao.getAllActiveVacanciesByCategoryId(categoryFromDB.getId());
            List<VacancyViewAllDto> vacancyDtoList = new ArrayList<>();

            for (Vacancy vacancy : vacancies) {
                User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

                UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

                Integer id = vacancy.getId();
                String name = vacancy.getName();
                String description = vacancy.getDescription();
                String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
                BigDecimal salary = vacancy.getSalary();
                Integer experienceFrom = vacancy.getExperienceFrom();
                Integer experienceTo = vacancy.getExperienceTo();
                LocalDateTime createdDate = vacancy.getCreatedDate();
                String formattedCreatedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(createdDate);

                LocalDateTime updateTime = vacancy.getUpdateTime();
                String formattedUpdateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(updateTime);

                Boolean isActive = vacancy.getIsActive();

                VacancyViewAllDto vacancyDto = VacancyViewAllDto.builder().id(id).user(userDto).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(formattedCreatedDate).updateTime(formattedUpdateTime).isActive(isActive).build();
                vacancyDtoList.add(vacancyDto);
            }
            return toPage(vacancyDtoList, PageRequest.of(pageNumber, pageSize));
        }

        List<Vacancy> vacancies = vacancyDao.getAllActiveVacancies();
        List<VacancyViewAllDto> vacancyDtoList = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().id(userEntity.getId()).name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = vacancy.getId();
            String name = vacancy.getName();
            String description = vacancy.getDescription();
            String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
            BigDecimal salary = vacancy.getSalary();
            Integer experienceFrom = vacancy.getExperienceFrom();
            Integer experienceTo = vacancy.getExperienceTo();
            LocalDateTime createdDate = vacancy.getCreatedDate();
            String formattedCreatedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(createdDate);

            LocalDateTime updateTime = vacancy.getUpdateTime();
            String formattedUpdateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(updateTime);

            Boolean isActive = vacancy.getIsActive();

            VacancyViewAllDto vacancyDto = VacancyViewAllDto.builder().id(id).user(userDto).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(formattedCreatedDate).updateTime(formattedUpdateTime).isActive(isActive).build();
            vacancyDtoList.add(vacancyDto);
        }
        return toPage(vacancyDtoList, PageRequest.of(pageNumber, pageSize));
    }


    private Page<VacancyViewAllDto> toPage(List<VacancyViewAllDto> resumes, Pageable pageable) {
        if (pageable.getOffset() >= resumes.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize() > resumes.size() ?
                resumes.size() : pageable.getOffset() + pageable.getPageSize()));
        List<VacancyViewAllDto> subList = resumes.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, resumes.size());
    }

    @Override
    @SneakyThrows
    public List<VacancyDto> getAllActiveVacanciesByName(String title) {
        List<Vacancy> vacancies = vacancyDao.getAllActiveVacanciesByName(title);

        List<VacancyDto> vacancyDtoList = new ArrayList<>();

        for (Vacancy vacancy : vacancies) {
            User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = vacancy.getId();
            String name = vacancy.getName();
            String description = vacancy.getDescription();
            String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
            BigDecimal salary = vacancy.getSalary();
            Integer experienceFrom = vacancy.getExperienceFrom();
            Integer experienceTo = vacancy.getExperienceTo();
            LocalDateTime createdDate = vacancy.getCreatedDate();
            LocalDateTime updateTime = vacancy.getUpdateTime();
            Boolean isActive = vacancy.getIsActive();

            VacancyDto vacancyDto = VacancyDto.builder().id(id).user(userDto).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(createdDate).updateTime(updateTime).isActive(isActive).build();
            vacancyDtoList.add(vacancyDto);
        }
        if (vacancyDtoList.isEmpty()) {
            throw new ResumeNotFoundException("Cannot find any vacancies with name " + title);
        }
        return vacancyDtoList;
    }

    @Override
    @SneakyThrows
    public List<VacancyDto> getAllActiveVacanciesByCategoryName(String categoriesName) {
        Category category = categoryDao.getCategoryByName(categoriesName).orElseThrow(() -> new CategoryNotFoundException("Cannot find any resume with category: " + categoriesName));

        List<Vacancy> vacancies = vacancyDao.getAllActiveVacanciesByCategoryId(category.getId());

        List<VacancyDto> vacancyDtoList = new ArrayList<>();

        for (Vacancy vacancy : vacancies) {
            User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = vacancy.getName();
            String description = vacancy.getDescription();
            String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
            BigDecimal salary = vacancy.getSalary();
            Integer experienceFrom = vacancy.getExperienceFrom();
            Integer experienceTo = vacancy.getExperienceTo();
            LocalDateTime createdDate = vacancy.getCreatedDate();
            LocalDateTime updateTime = vacancy.getUpdateTime();
            Boolean isActive = vacancy.getIsActive();

            VacancyDto vacancyDto = VacancyDto.builder().user(userDto).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(createdDate).updateTime(updateTime).isActive(isActive).build();
            vacancyDtoList.add(vacancyDto);
        }
        if (vacancyDtoList.isEmpty()) {
            throw new ResumeNotFoundException("Cannot find any vacancies with categoryName " + categoriesName);
        }
        return (vacancyDtoList);
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
    public List<VacancyDto> getAllVacanciesOfEmployer(Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());

        List<Vacancy> vacancies = vacancyDao.getAllVacanciesOfEmployer(user.getId());

        List<VacancyDto> vacancyDtoList = new ArrayList<>();

        for (Vacancy vacancy : vacancies) {
            User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            String name = vacancy.getName();
            String description = vacancy.getDescription();
            String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
            BigDecimal salary = vacancy.getSalary();
            Integer experienceFrom = vacancy.getExperienceFrom();
            Integer experienceTo = vacancy.getExperienceTo();
            LocalDateTime createdDate = vacancy.getCreatedDate();
            LocalDateTime updateTime = vacancy.getUpdateTime();
            Boolean isActive = vacancy.getIsActive();

            VacancyDto vacancyDto = VacancyDto.builder().user(userDto).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(createdDate).updateTime(updateTime).isActive(isActive).build();
            vacancyDtoList.add(vacancyDto);
        }
        if (vacancyDtoList.isEmpty()) {
            throw new ResumeNotFoundException("You don't have any vacancies");
        }
        return vacancyDtoList;
    }

    @Override
    @SneakyThrows
    public void createVacancyForEmployer(VacancyCreateDto vacancyDto, Integer userId) {
        User user = userDao.getUserById(userId).orElseThrow(() -> new UserNotFoundException("Can't find user"));
        Category category = categoryDao.getCategoryByName(vacancyDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));
        if (vacancyDto.getExperienceFrom() > vacancyDto.getExperienceTo()) {
            throw new VacancyNotFoundException("ExperienceTo cannot be less then experienceFrom");
        }

        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategoryId(category.getId());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExperienceFrom(vacancyDto.getExperienceFrom());
        vacancy.setExperienceTo(vacancyDto.getExperienceTo());
        vacancy.setIsActive(vacancyDto.getIsActive());
        vacancy.setAuthorId(user.getId());
        vacancyDao.createVacancy(vacancy);
    }

    @Override
    @SneakyThrows
    public List<VacancyDto> getAllVacanciesOfEmployerById(Integer userId) {
        User user = userDao.getUserById(userId).orElseThrow(() -> new UserNotFoundException("Cant find user"));

        List<Vacancy> vacancies = vacancyDao.getAllVacanciesOfEmployer(user.getId());

        List<VacancyDto> vacancyDtoList = new ArrayList<>();

        for (Vacancy vacancy : vacancies) {
            User userEntity = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = vacancy.getId();
            String name = vacancy.getName();
            String description = vacancy.getDescription();
            String categoryName = categoryDao.getCategoryById(vacancy.getCategoryId()).getName();
            BigDecimal salary = vacancy.getSalary();
            Integer experienceFrom = vacancy.getExperienceFrom();
            Integer experienceTo = vacancy.getExperienceTo();
            LocalDateTime createdDate = vacancy.getCreatedDate();
            LocalDateTime updateTime = vacancy.getUpdateTime();
            Boolean isActive = vacancy.getIsActive();

            VacancyDto vacancyDto = VacancyDto.builder().user(userDto).id(id).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(createdDate).updateTime(updateTime).isActive(isActive).build();
            vacancyDtoList.add(vacancyDto);
        }
        return vacancyDtoList;
    }


    @Override
    @SneakyThrows
    public void editVacancy(VacancyEditDto vacancyDto, Integer id) {
        Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(() -> new ResumeNotFoundException("Can't find vacancy with id " + id));

//        if (!vacancyDao.isEmployerHasVacancyById(user, id)) {
//            throw new ResumeNotFoundException("Can't find your vacancy with this id");
//        }

        vacancy.setId(id);
        if (vacancyDto.getName() != null) {
            vacancy.setName(vacancyDto.getName());
        }

        if (vacancyDto.getDescription() != null) {
            vacancy.setDescription(vacancyDto.getDescription());
        }

        Category category;
        if (vacancyDto.getCategoryName() != null) {
            category = categoryDao.getCategoryByName(vacancyDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));
            vacancy.setCategoryId(category.getId());
        }

        if (vacancyDto.getSalary() != null) {
            vacancy.setSalary(vacancyDto.getSalary());
        }

        if (vacancyDto.getExperienceFrom() != null && vacancyDto.getExperienceTo() != null) {

            vacancy.setExperienceFrom(vacancyDto.getExperienceFrom());
            vacancy.setExperienceTo(vacancyDto.getExperienceTo());

            if (vacancyDto.getExperienceFrom() > vacancyDto.getExperienceTo()) {
                throw new VacancyNotFoundException("ExperienceTo cannot be less then experienceFrom");
            }
        }


        if (Boolean.TRUE.equals(vacancyDto.getIsActive())) {
            vacancy.setIsActive(true);
        } else {
            vacancy.setIsActive(false);
        }

        vacancyDao.editVacancy(vacancy);
    }

    @Override
    @SneakyThrows
    public void deleteVacancyById(Integer vacancyId, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        vacancyDao.getVacancyById(vacancyId).orElseThrow(() -> new ResumeNotFoundException("Can't find vacancy with this id"));
        if (!vacancyDao.isEmployerHasVacancyById(user, vacancyId)) {
            throw new ResumeNotFoundException("Can't find your vacancy with this id");
        }

        vacancyDao.deleteVacancyById(vacancyId);
    }

    @Override
    public List<VacancyDto> getVacanciesBySalary(boolean ascending) {
        List<Vacancy> vacancies = ascending ? vacancyDao.getAllActiveVacanciesBySalaryAscending() : vacancyDao.getAllActiveVacanciesBySalaryDescending();
        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<VacancyDto> getVacanciesByUpdateTime(boolean ascending) {
        List<Vacancy> vacancies = ascending ? vacancyDao.getAllActiveVacanciesByUpdateTimeAscending() : vacancyDao.getAllActiveVacanciesByUpdateTimeDescending();
        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    private VacancyDto convertToDto(Vacancy vacancy) {
        User user = userDao.getUserById(vacancy.getAuthorId()).orElseThrow(() -> new UserNotFoundException("asdf"));
        return VacancyDto.builder().name(vacancy.getName()).description(vacancy.getDescription()).categoryName(categoryDao.getCategoryById(vacancy.getCategoryId()).getName()).salary(vacancy.getSalary()).experienceFrom(vacancy.getExperienceFrom()).experienceTo(vacancy.getExperienceTo()).isActive(vacancy.getIsActive()).createdDate(vacancy.getCreatedDate()).updateTime(vacancy.getUpdateTime()).user(convertUserToUserForVacancyPrintDto(user)).build();
    }

    private UserForVacancyPrintDto convertUserToUserForVacancyPrintDto(User user) {
        if (user == null) {
            return null;
        }

        return UserForVacancyPrintDto.builder().name(user.getName()).age(user.getAge()).email(user.getEmail()).phoneNumber(user.getPhoneNumber()).avatar(user.getAvatar()).build();
    }
}
