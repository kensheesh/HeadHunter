//package kg.attractor.headhunter.service.impl;
//
//import kg.attractor.headhunter.dao.VacancyDao;
//import kg.attractor.headhunter.dto.VacancyDto;
//import kg.attractor.headhunter.exception.UserNotFoundException;
//import kg.attractor.headhunter.exception.VacancyNotFoundException;
//import kg.attractor.headhunter.model.User;
//import kg.attractor.headhunter.model.Vacancy;
//import kg.attractor.headhunter.service.VacancyService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class VacancyServiceImpl implements VacancyService {
//    private final VacancyDao vacancyDao;
//    private final UserDao userDao;
//
//    private VacancyDto convertToDto(Vacancy e) {
//        return VacancyDto.builder()
//                .id(e.getId())
//                .name(e.getName())
//                .description(e.getDescription())
//                .categoryId(e.getCategoryId())
//                .salary(e.getSalary())
//                .experienceFrom(e.getExperienceFrom())
//                .experienceTo(e.getExperienceTo())
//                .isActive(e.isActive())
//                .authorId(e.getAuthorId())
//                .createdDate(e.getCreatedDate())
//                .updateTime(e.getUpdateTime())
//                .build();
//    }
//
//    @Override
//    public List<VacancyDto> getVacancies(int userId) throws UserNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (userId > userDao.getUsers().size() || userId < 1) {
//            log.error("UserNotFoundException for ID: {}", userId);
//            throw new UserNotFoundException("Don't have access");
//        }
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("EMPLOYER")) {
//                log.error("Access denied for employer with ID: {}", userId);
//                throw new UserNotFoundException("Don't have access");
//            }
//        }
//
//        return vacancyDao.getVacancies().stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public VacancyDto getVacancyById(int id) throws VacancyNotFoundException {
//        log.info("Fetching vacancy by ID: {}", id);
//        Vacancy vacancy = vacancyDao.getVacancyById(id)
//                .orElseThrow(() -> {
//                    log.error("VacancyNotFoundException for ID: {}", id);
//                    return new VacancyNotFoundException("Can't find vacancy with id: " + id);
//                });
//        VacancyDto dto = convertToDto(vacancy);
//        log.info("Retrieved vacancy with ID: {}", id);
//        return dto;
//    }
//
//    @Override
//    public List<VacancyDto> getVacanciesByName(String name) throws VacancyNotFoundException {
//        List<Vacancy> vacancies = vacancyDao.getVacancyByName(name);
//        if (vacancies.isEmpty()) {
//            throw new VacancyNotFoundException("Can't find vacancy with this name: " + name);
//        }
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getVacanciesByCategoryId(int categoryId) throws VacancyNotFoundException {
//        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategoryId(categoryId);
//        if (vacancies.isEmpty() || categoryId == 0) {
//            throw new VacancyNotFoundException("Can't find vacancy with this category: " + categoryId);
//        }
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getVacanciesByCategoryName(String categoryName, int userId) throws VacancyNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (userId > userDao.getUsers().size() || userId < 1) {
//            throw new VacancyNotFoundException("Don't have access");
//        }
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("EMPLOYER")) {
//                throw new VacancyNotFoundException("Don't have access");
//            }
//        }
//
//        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategoryName(categoryName);
//        if (vacancies.isEmpty()) {
//            throw new VacancyNotFoundException("Can't find vacancies for category: " + categoryName);
//        }
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getVacanciesByUserId(int userId) throws VacancyNotFoundException {
//        List<Vacancy> vacancies = vacancyDao.getVacanciesByUserId(userId);
//        if (vacancies.isEmpty() || userId == 0) {
//            throw new VacancyNotFoundException("Can't find vacancy with this userId: " + userId);
//        }
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getActiveVacancies() {
//        return vacancyDao.getActiveVacancies().stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getActiveVacanciesByUserId(int userId) throws VacancyNotFoundException {
//        List<Vacancy> vacancies = vacancyDao.getActiveVacanciesByUserId(userId);
//        if (vacancies.isEmpty() || userId == 0) {
//            throw new VacancyNotFoundException("Can't find active vacancy with this userId: " + userId);
//        }
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getVacanciesBySalary(boolean ascending) {
//        List<Vacancy> vacancies = ascending
//                ? vacancyDao.getVacanciesBySalaryAscending()
//                : vacancyDao.getVacanciesBySalaryDescending();
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<VacancyDto> getVacanciesByUpdateTime(boolean ascending) {
//        List<Vacancy> vacancies = ascending
//                ? vacancyDao.getVacanciesByUpdateTimeAscending()
//                : vacancyDao.getVacanciesByUpdateTimeDescending();
//        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public void createVacancy(VacancyDto vacancyDto, int userId) throws UserNotFoundException {
//        log.info("Creating vacancy: {} by user ID: {}", vacancyDto.getName(), userId);
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (userId > userDao.getUsers().size() || userId < 1) {
//            throw new UserNotFoundException("Don't have access");
//        }
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("APPLICANT")) {
//                throw new UserNotFoundException("Don't have access");
//            }
//        }
//
//        Vacancy vacancy = new Vacancy();
//        vacancy.setName(vacancyDto.getName());
//        vacancy.setDescription(vacancyDto.getDescription());
//        vacancy.setCategoryId(vacancyDto.getCategoryId());
//        vacancy.setSalary(vacancyDto.getSalary());
//        vacancy.setExperienceFrom(vacancyDto.getExperienceFrom());
//        vacancy.setExperienceTo(vacancyDto.getExperienceTo());
//        vacancy.setActive(vacancyDto.isActive());
//        vacancy.setAuthorId(vacancyDto.getAuthorId());
//        vacancy.setCreatedDate(vacancyDto.getCreatedDate());
//        vacancy.setUpdateTime(vacancyDto.getUpdateTime());
//        log.info("Vacancy created successfully with ID: {}", vacancy.getId());
//        vacancyDao.createVacancy(vacancy);
//    }
//
//    @Override
//    public void editVacancy(VacancyDto vacancyDto, int userId) throws UserNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (userId > userDao.getUsers().size() || userId < 1) {
//            throw new UserNotFoundException("Don't have access");
//        }
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("APPLICANT")) {
//                throw new UserNotFoundException("Don't have access");
//            }
//        }
//        Vacancy vacancy = new Vacancy();
//        vacancy.setId(vacancyDto.getId());
//        vacancy.setName(vacancyDto.getName());
//        vacancy.setDescription(vacancyDto.getDescription());
//        vacancy.setCategoryId(vacancyDto.getCategoryId());
//        vacancy.setSalary(vacancyDto.getSalary());
//        vacancy.setExperienceFrom(vacancyDto.getExperienceFrom());
//        vacancy.setExperienceTo(vacancyDto.getExperienceTo());
//        vacancy.setActive(vacancyDto.isActive());
//        vacancy.setAuthorId(vacancyDto.getAuthorId());
//        vacancy.setCreatedDate(vacancyDto.getCreatedDate());
//        vacancy.setUpdateTime(vacancyDto.getUpdateTime());
//
//        vacancyDao.editVacancy(vacancy);
//    }
//
//    @Override
//    public void deleteVacancyById(int id, int userId) throws UserNotFoundException {
//        Optional<User> user = userDao.getUserById(userId);
//
//        if (userId > userDao.getUsers().size() || userId < 1) {
//            throw new UserNotFoundException("Don't have access");
//        }
//        if (user.isPresent()) {
//            if (user.get().getAccountType().name().equals("APPLICANT")) {
//                throw new UserNotFoundException("Don't have access");
//            }
//        }
//
//        vacancyDao.deleteVacancyById(id);
//    }
//}
