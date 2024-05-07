package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
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
    private final UserRepository userRepository;


    @GetMapping("/updateTime/{id}")
    public String updateResumesUpdateTime(@PathVariable Integer id) {
        vacancyService.updateVacancyUpdateTime(id);
        return "redirect:/profile";
    }

    @GetMapping
    public String viewAllVacancies(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page,
                                   @RequestParam(name = "category", defaultValue = "default") String category) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            System.out.println(accountType);
            model.addAttribute("type", accountType);
        }
        //--------------------------------------------------------------------------------------------------------------

        if (category.equalsIgnoreCase("выбрать категорию (все)")) {
            category = "default";
        }

        Page<VacancyViewAllDto> vacanciesPage = vacancyService.getAllActiveVacancies(page, 5, category);
        model.addAttribute("vacanciesPage", vacanciesPage);
        model.addAttribute("category", category);
        return "vacancies/all_vacancies";
    }

    @GetMapping("/{vacancyId}")
    public String getVacancyById(@PathVariable Integer vacancyId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }
        model.addAttribute("vacancy", vacancyService.getVacancyById(vacancyId));
        return "vacancies/vacancy_info";
    }

    @GetMapping("/create")
    public String createVacancy(Model model, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        model.addAttribute("user", userService.getUserByAuth(authentication));
        return "vacancies/create_vacancy";
    }

    @PostMapping("/create")
    public String createVacancy(@Valid VacancyCreateDto vacancyDto, Authentication authentication) {
        System.out.println(vacancyDto.getIsActive());
        UserDto user = userService.getUserByAuth(authentication);
        vacancyService.createVacancyForEmployer(vacancyDto, user.getId());
        return "redirect:/profile";
    }


    @GetMapping("/edit/{vacancyId}")
    public String editVacancy(Model model, @PathVariable Integer vacancyId) {
        VacancyViewEditDto vacancyDto = vacancyService.getVacancyByIdForEdit(vacancyId);
        model.addAttribute("vacancy", vacancyDto);
        return "vacancies/edit_vacancy";
    }

    @PostMapping("/edit/{vacancyId}")
    public String editVacancy(@Valid VacancyEditDto vacancyEditDto, @PathVariable Integer vacancyId) {
        vacancyService.editVacancy(vacancyEditDto, vacancyId);
        return "redirect:/profile";
    }

    @PostMapping("/delete/{vacancyId}")
    public String deleteVacancy(@PathVariable Integer vacancyId, Authentication authentication) {
        vacancyService.deleteVacancyById(vacancyId, authentication);
        return "redirect:/profile";
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
