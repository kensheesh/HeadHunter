package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dao.RespondedApplicantDao;
import kg.attractor.headhunter.dto.RespondToVacancyDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ApplyingController {
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final UserService userService;
    private final RespondedApplicantServiceImpl respondedApplicantService;
    private final RespondedApplicantDao respondedApplicantDao;
    private final UserRepository userRepository;

    @GetMapping("apply/vacancy/{vacancyId}")
    public String applyForVacancy(@PathVariable Integer vacancyId, Model model, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        model.addAttribute("vacancy", vacancyService.getVacancyById(vacancyId));
        model.addAttribute("user", userService.getUserByAuth(authentication));
        model.addAttribute("resumes", resumeService.getAllResumesOfApplicant(authentication));
        return "applying/apply_for_vacancy";
    }

    @PostMapping("apply/vacancy/{vacancyId}")
    public String applyForVacancy(@Valid RespondToVacancyDto respondToVacancyDto, Authentication authentication) {
        respondedApplicantService.createRespondedApplicant(respondToVacancyDto, authentication);
        RespondedApplicant respondedApplicant = respondedApplicantDao.getRespondedApplicantByResumeIdAndVacancyId(respondToVacancyDto.getVacancyId(), respondToVacancyDto.getResumeId());
        return "redirect:/chat/" + respondedApplicant.getId();
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
