//package kg.attractor.headhunter.controller;
//
//import kg.attractor.headhunter.dto.UserDto;
//import kg.attractor.headhunter.dto.VacancyDto;
//import kg.attractor.headhunter.exception.UserNotFoundException;
//import kg.attractor.headhunter.exception.VacancyNotFoundException;
//import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/vacancies")
//@RequiredArgsConstructor
//public class RespondedApplicantController {
//    private final RespondedApplicantServiceImpl respondedApplicantService;
//
//    @GetMapping("/{id}/responded-vacancies")
//    public ResponseEntity<?> getVacanciesForRespondedApplicantsByUserId(@PathVariable int id,
//                                                                        @RequestParam int userId) {
//        try {
//            List<VacancyDto> vacancyDto = respondedApplicantService.getVacanciesForRespondedApplicantsByUserId(id, userId);
//            return ResponseEntity.ok(vacancyDto);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/{vacancyId}/respondents")
//    public ResponseEntity<?> getRespondedUsersForVacancies(@PathVariable int vacancyId,
//                                                           @RequestParam int userId) {
//        try {
//            List<UserDto> userDto = respondedApplicantService.getRespondedUsersForVacancies(vacancyId, userId);
//            return ResponseEntity.ok(userDto);
//        } catch (UserNotFoundException | VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//}
