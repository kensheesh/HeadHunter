//package kg.attractor.headhunter.service.impl;
//
//import kg.attractor.headhunter.dao.RespondedApplicantDao;
//import kg.attractor.headhunter.dto.VacancyDto;
//import kg.attractor.headhunter.exception.UserNotFoundException;
//import kg.attractor.headhunter.exception.VacancyNotFoundException;
//import kg.attractor.headhunter.model.User;
//import kg.attractor.headhunter.model.Vacancy;
//import kg.attractor.headhunter.service.RespondedApplicantService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class RespondedApplicantServiceImpl implements RespondedApplicantService {
//    private final RespondedApplicantDao respondedApplicantDao;
//    private final UserDao userDao;
//
//
//    @Override
//    public List<VacancyDto> getVacanciesForRespondedApplicantsByUserId(int id, int userId) throws VacancyNotFoundException {
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
//        List<Vacancy> vacancies = respondedApplicantDao.getVacanciesForRespondedApplicantsByUserId(id);
//        if (vacancies.isEmpty()) {
//            throw new VacancyNotFoundException("Can't find vacancy with this id: " + id);
//        }
//        List<VacancyDto> dtos = new ArrayList<>();
//        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
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
//                .build()));
//        return dtos;
//    }
//
//    @Override
//    public List<UserDto> getRespondedUsersForVacancies(int vacancyId, int userId) throws UserNotFoundException, VacancyNotFoundException {
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
//
//        List<User> users = respondedApplicantDao.getRespondedUsersForVacancies(vacancyId);
//        if (users.isEmpty()) {
//            throw new VacancyNotFoundException("Can't find vacancy with this id: " + vacancyId);
//        }
//        List<UserDto> dtos = new ArrayList<>();
//        users.forEach(e -> dtos.add(UserDto.builder()
//                .id(e.getId())
//                .name(e.getName())
//                .surname(e.getSurname())
//                .age(e.getAge())
//                .email(e.getEmail())
//                .password(e.getPassword())
//                .phoneNumber(e.getPhoneNumber())
//                .avatar(e.getAvatar())
//                .accountType(e.getAccountType())
//                .build()));
//        return dtos;
//    }
//}
