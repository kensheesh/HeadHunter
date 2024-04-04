package kg.attractor.headhunter.controller.api;

import kg.attractor.headhunter.dto.VacancyForRespondedDto;
import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class RespondedApplicantController {
    private final RespondedApplicantServiceImpl respondedApplicantService;

    @GetMapping("/{id}/responded-vacancies")
    public ResponseEntity<?> getVacanciesForRespondedApplicantsByUserId(@PathVariable Integer id, Authentication authentication) {
        List<VacancyForRespondedDto> vacancyDto = respondedApplicantService.getVacanciesForRespondedApplicantsByUserId(authentication);
        return ResponseEntity.ok(vacancyDto);
    }

    @GetMapping("/{vacancyId}/respondents")
    public ResponseEntity<?> getRespondedUsersForVacancies(@PathVariable Integer vacancyId, Authentication authentication) {
        return ResponseEntity.ok(respondedApplicantService.getRespondedUsersForVacancies(vacancyId, authentication));
    }
}
