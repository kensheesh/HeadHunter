package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.RespondedApplicantDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RespondedApplicantController {
    private final RespondedApplicantServiceImpl respondedApplicantService;

    @GetMapping("respondedApplicants/userId{userId}")
    public ResponseEntity<?> getVacanciesForRespondedApplicantsByUserId(@PathVariable int userId) {
        try {
            List<VacancyDto> vacancyDto = respondedApplicantService.getVacanciesForRespondedApplicantsByUserId(userId);
            return ResponseEntity.ok(vacancyDto);
        } catch (VacancyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("respondedApplicants/respondedUsers{vacancyId}")
    public ResponseEntity<?> getRespondedUsersForVacancies(@PathVariable int vacancyId) {
        try {
            List<UserDto> userDto = respondedApplicantService.getRespondedUsersForVacancies(vacancyId);
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
