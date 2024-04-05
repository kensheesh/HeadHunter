package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/{vacancyId}")
    public String getResumeById(@PathVariable Integer vacancyId, Model model) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(vacancyId));
        return "vacancy_info";
    }
}
