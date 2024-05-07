package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.CategoryNotFoundException;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.model.Category;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.model.Vacancy;
import kg.attractor.headhunter.repository.CategoryRepository;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.repository.VacancyRepository;
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
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @SneakyThrows
    public void updateVacancyUpdateTime(Integer id) {
        LocalDateTime now = LocalDateTime.now();
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new VacancyNotFoundException("Can't find vacancy with id: " + id));
        vacancy.setUpdateTime(now);
        vacancyRepository.save(vacancy);
    }


    @Override
    @SneakyThrows
    public VacancyDto getVacancyById(Integer id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new VacancyNotFoundException("Can't find vacancy with id: " + id));
        User user = userRepository.findById(vacancy.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("can't find user with this id"));
        UserForVacancyPrintDto userDto = new UserForVacancyPrintDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAvatar(user.getAvatar());
        return VacancyDto.builder().id(vacancy.getId()).name(vacancy.getName()).description(vacancy.getDescription()).categoryName(vacancy.getCategory().getName()).salary(vacancy.getSalary()).experienceFrom(vacancy.getExperienceFrom()).experienceTo(vacancy.getExperienceTo()).isActive(vacancy.getIsActive()).createdDate(vacancy.getCreatedDate()).updateTime(vacancy.getUpdateTime()).user(userDto).build();
    }

    @Override
    @SneakyThrows
    public VacancyViewEditDto getVacancyByIdForEdit(Integer id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new VacancyNotFoundException("Can't find vacancy with id: " + id));
        User user = userRepository.findById(vacancy.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("can't find user with this id"));
        UserForVacancyPrintDto userDto = new UserForVacancyPrintDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAvatar(user.getAvatar());

        Integer salary = vacancy.getSalary().intValue();
        return VacancyViewEditDto.builder().id(vacancy.getId()).name(vacancy.getName()).description(vacancy.getDescription()).categoryName(vacancy.getCategory().getName()).salary(salary).experienceFrom(vacancy.getExperienceFrom()).experienceTo(vacancy.getExperienceTo()).isActive(vacancy.getIsActive()).createdDate(vacancy.getCreatedDate()).updateTime(vacancy.getUpdateTime()).user(userDto).build();
    }

    @SneakyThrows
    @Override
    public Page<VacancyViewAllDto> getAllActiveVacancies(int pageNumber, int pageSize, String category) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Vacancy> vacanciesPage;
        if (!category.equalsIgnoreCase("default")) {
            Category categoryFromDB = categoryRepository.findByName(category)
                    .orElseThrow(() -> new CategoryNotFoundException("Cannot find any resume with category: " + category));
            vacanciesPage = vacancyRepository.findByCategoryIdAndIsActive(categoryFromDB.getId(), true, pageable);
        } else {
            vacanciesPage = vacancyRepository.findByIsActive(true, pageable);
        }

        return vacanciesPage.map(this::createVacancyDto);
    }

    @SneakyThrows
    private VacancyViewAllDto createVacancyDto(Vacancy vacancy) {
        User userEntity = userRepository.findById(vacancy.getAuthor().getId())
                .orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

        UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .avatar(userEntity.getAvatar())
                .build();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedCreatedDate = dateFormatter.format(vacancy.getCreatedDate());
        String formattedUpdateTime = dateFormatter.format(vacancy.getUpdateTime());

        return VacancyViewAllDto.builder()
                .id(vacancy.getId())
                .user(userDto)
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryName(vacancy.getCategory().getName())
                .salary(vacancy.getSalary())
                .experienceFrom(vacancy.getExperienceFrom())
                .experienceTo(vacancy.getExperienceTo())
                .createdDate(formattedCreatedDate)
                .updateTime(formattedUpdateTime)
                .isActive(vacancy.getIsActive())
                .build();
    }


    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user"));
    }


    @Override
    @SneakyThrows
    public void createVacancyForEmployer(VacancyCreateDto vacancyDto, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Can't find user"));
        Category category = categoryRepository.findByName(vacancyDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));
        if (vacancyDto.getExperienceFrom() > vacancyDto.getExperienceTo()) {
            throw new VacancyNotFoundException("ExperienceTo cannot be less then experienceFrom");
        }

        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategory(category);
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setCreatedDate(LocalDateTime.now());
        vacancy.setUpdateTime(LocalDateTime.now());
        vacancy.setExperienceFrom(vacancyDto.getExperienceFrom());
        vacancy.setExperienceTo(vacancyDto.getExperienceTo());
        if (vacancyDto.getIsActive() == null) {
            vacancy.setIsActive(false);
        } else {
            vacancy.setIsActive(vacancyDto.getIsActive());
        }
        vacancy.setAuthor(user);
        vacancyRepository.save(vacancy);
    }

    @Override
    @SneakyThrows
    public List<VacancyViewAllDto> getAllVacanciesOfEmployerById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cant find user"));

        List<Vacancy> vacancies = vacancyRepository.findByAuthorId(user.getId());

        List<VacancyViewAllDto> vacancyDtoList = new ArrayList<>();

        for (Vacancy vacancy : vacancies) {
            User userEntity = userRepository.findById(vacancy.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException("Cannot find user with this id"));

            UserForVacancyPrintDto userDto = UserForVacancyPrintDto.builder().name(userEntity.getName()).age(userEntity.getAge()).email(userEntity.getEmail()).phoneNumber(userEntity.getPhoneNumber()).avatar(userEntity.getAvatar()).build();

            Integer id = vacancy.getId();
            String name = vacancy.getName();
            String description = vacancy.getDescription();
            String categoryName = vacancy.getCategory().getName();
            BigDecimal salary = vacancy.getSalary();
            Integer experienceFrom = vacancy.getExperienceFrom();
            Integer experienceTo = vacancy.getExperienceTo();
            LocalDateTime createdDate = vacancy.getCreatedDate();
            LocalDateTime updateTime = vacancy.getUpdateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String formattedUpdateTime = updateTime.format(formatter);
            String formattedCreatedDate = createdDate.format(formatter);
            Boolean isActive = vacancy.getIsActive();

            VacancyViewAllDto vacancyDto = VacancyViewAllDto.builder().user(userDto).id(id).name(name).description(description).categoryName(categoryName).salary(salary).experienceFrom(experienceFrom).experienceTo(experienceTo).createdDate(formattedCreatedDate).updateTime(formattedUpdateTime).isActive(isActive).build();
            vacancyDtoList.add(vacancyDto);
        }
        return vacancyDtoList;
    }


    @Override
    @SneakyThrows
    public void editVacancy(VacancyEditDto vacancyDto, Integer id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new ResumeNotFoundException("Can't find vacancy with id " + id));

        vacancy.setId(id);
        if (vacancyDto.getName() != null) {
            vacancy.setName(vacancyDto.getName());
        }

        if (vacancyDto.getDescription() != null) {
            vacancy.setDescription(vacancyDto.getDescription());
        }

        Category category;
        if (vacancyDto.getCategoryName() != null) {
            category = categoryRepository.findByName(vacancyDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Cannot find this category"));
            vacancy.setCategory(category);
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

        vacancyRepository.save(vacancy);
    }

    @Override
    @SneakyThrows
    public void deleteVacancyById(Integer vacancyId, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new ResumeNotFoundException("Can't find vacancy with this id"));
        if (!vacancy.getAuthor().getId().equals(user.getId())) {
            throw new ResumeNotFoundException("Can't find your vacancy with this id");
        }

        vacancyRepository.deleteById(vacancyId);
    }
}
