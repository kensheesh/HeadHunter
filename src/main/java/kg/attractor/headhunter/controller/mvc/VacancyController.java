package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping
    public String viewAllVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getAllActiveVacancies());
        return "vacancies/all_vacancies";
    }

    @GetMapping("/{vacancyId}")
    public String getResumeById(@PathVariable Integer vacancyId, Model model) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(vacancyId));
        return "vacancies/vacancy_info";
    }

    @GetMapping("/create/{userId}")
    public String createVacancy(Model model, @PathVariable Integer userId) {
        model.addAttribute("user", userService.getUserById(userId));
        return "vacancies/create_vacancy";
    }

    @PostMapping("/create/{userId}")
    public String createVacancy(@Valid VacancyCreateDto vacancyDto, @PathVariable Integer userId) {
        vacancyService.createVacancyForEmployer(vacancyDto, userId);
        return "redirect:/users/" + userId;
    }


    @GetMapping("/edit/{vacancyId}")
    public String editVacancy(Model model, @PathVariable Integer vacancyId) {
        VacancyDto vacancyDto = vacancyService.getVacancyById(vacancyId);
        model.addAttribute("vacancy", vacancyDto);
        return "vacancies/edit_vacancy";
    }

    @PostMapping("/edit/{vacancyId}")
    public String editVacancy(@Valid VacancyEditDto vacancyEditDto, @PathVariable Integer vacancyId) {
        vacancyService.editVacancy(vacancyEditDto, vacancyId);
        return "redirect:/vacancies";
    }

}
