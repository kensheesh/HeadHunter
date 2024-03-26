package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.RespondedApplicantDao;
import kg.attractor.headhunter.dao.ResumeDao;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dao.VacancyDao;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;
    private final UserDao userDao;
    private final ResumeDao resumeDao;
    private final VacancyDao vacancyDao;


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
    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user"));
    }

    @Override
    @SneakyThrows
    public List<UserDto> getRespondedUsersForVacancies(Integer vacancyId, Authentication authentication) {
        User currentUser = getUserFromAuth(authentication.getPrincipal().toString());

        if (!vacancyDao.isEmployerHasVacancyById(currentUser, vacancyId)) {
            throw new VacancyNotFoundException("Vacancy not found or access denied.");
        }

        List<User> users = respondedApplicantDao.getRespondedUsersForVacancies(vacancyId);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        return users.stream().map(this::convertToUserDto).collect(Collectors.toList());
    }

    private UserDto convertToUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }
}
