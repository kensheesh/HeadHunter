package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.RespondedApplicantDao;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.model.Vacancy;
import kg.attractor.headhunter.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public List<VacancyDto> getVacanciesForRespondedApplicantsByUserId(int userId) throws VacancyNotFoundException {
        List<Vacancy> vacancies = respondedApplicantDao.getVacanciesForRespondedApplicantsByUserId(userId).orElseThrow(() -> new VacancyNotFoundException("Can't find resume with this userId: " + userId));
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .categoryId(e.getCategoryId())
                .salary(e.getSalary())
                .experienceFrom(e.getExperienceFrom())
                .experienceTo(e.getExperienceTo())
                .isActive(e.isActive())
                .authorId(e.getAuthorId())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdateTime())
                .build()));
        return dtos;
    }

    @Override
    public List<UserDto> getRespondedUsersForVacancies(int vacancyId) throws UserNotFoundException {
        List<User> users = respondedApplicantDao.getRespondedUsersForVacancies(vacancyId).orElseThrow(() -> new UserNotFoundException("Can't find user with this userId: "));
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(UserDto.builder()
                .id(e.getId())
                .name(e.getName())
                .surname(e.getSurname())
                .age(e.getAge())
                .email(e.getEmail())
                .password(e.getPassword())
                .phoneNumber(e.getPhoneNumber())
                .avatar(e.getAvatar())
                .accountType(e.getAccountType())
                .build()));
        return dtos;
    }
}
