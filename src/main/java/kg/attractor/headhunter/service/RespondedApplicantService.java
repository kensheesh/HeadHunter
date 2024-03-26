package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RespondedApplicantService {
//    List<VacancyDto> getVacanciesForRespondedApplicantsByUserId(int id, int userId) throws VacancyNotFoundException;

    List<UserDto> getRespondedUsersForVacancies(Integer vacancyId, Authentication authentication);
}
