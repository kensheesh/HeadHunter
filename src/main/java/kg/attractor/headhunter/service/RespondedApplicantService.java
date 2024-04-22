package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.*;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RespondedApplicantService {
    List<VacancyForRespondedDto> getVacanciesForRespondedApplicantsByUserId(Authentication authentication);
    List<RespondedApplicantDtoForChat> getRespondedApplicantDtoForChatByUserId(Authentication authentication);

    List<UserDto> getRespondedUsersForVacancies(Integer vacancyId, Authentication authentication);
    void createRespondedApplicant(RespondToVacancyDto respondToVacancyDto, Authentication authentication);
}
