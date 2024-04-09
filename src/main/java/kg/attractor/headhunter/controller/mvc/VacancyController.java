package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.VacancyCreateDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.dto.VacancyEditDto;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping()
    public String viewAllVacancies(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<VacancyDto> vacanciesPage = vacancyService.getAllActiveVacancies(page, 5);
        model.addAttribute("vacanciesPage", vacanciesPage);
        return "vacancies/all_vacancies";
    }

    @GetMapping("/filter")
    public String filterVacanciesByCategory(Model model, @RequestParam(name = "category") String categoryName,
                                            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<VacancyDto> filteredVacancies = vacancyService.getAllActiveVacanciesByCategoryName(categoryName, page, 5);
        model.addAttribute("vacanciesPage", filteredVacancies);
        model.addAttribute("selectedCategory", categoryName); // Добавляем выбранную категорию в модель
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
