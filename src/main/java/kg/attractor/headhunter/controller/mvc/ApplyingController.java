package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dao.RespondedApplicantDao;
import kg.attractor.headhunter.dto.RespondToVacancyDto;
import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @GetMapping("apply/vacancy/{vacancyId}")
    public String applyForVacancy(@PathVariable Integer vacancyId, Model model, Authentication auth) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(vacancyId));
        model.addAttribute("user", userService.getUserByAuth(auth));
        model.addAttribute("resumes", resumeService.getAllResumesOfApplicant(auth));
        return "applying/apply_for_vacancy";
    }

    @PostMapping("apply/vacancy/{vacancyId}")
    public String applyForVacancy(@Valid RespondToVacancyDto respondToVacancyDto, Authentication authentication) {
        System.out.println(respondToVacancyDto);
        respondedApplicantService.createRespondedApplicant(respondToVacancyDto, authentication);
        RespondedApplicant respondedApplicant = respondedApplicantDao.getRespondedApplicantByResumeIdAndVacancyId(respondToVacancyDto.getVacancyId(), respondToVacancyDto.getResumeId());
        return "redirect:/chat/"+respondedApplicant.getId();
    }
}
