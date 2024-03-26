package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.VacancyForRespondedDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RespondedApplicantService {
    List<VacancyForRespondedDto> getVacanciesForRespondedApplicantsByUserId(Authentication authentication);

    List<UserDto> getRespondedUsersForVacancies(Integer vacancyId, Authentication authentication);
}
