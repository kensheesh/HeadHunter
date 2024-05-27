package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeViewAllDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {
    private final ResumeService resumeService;
    private final UserRepository userRepository;

    @GetMapping("/updateTime/{id}")
    public String updateResumesUpdateTime(@PathVariable Integer id, Authentication authentication) {
        resumeService.updateResumeTime(id, authentication);
        return "redirect:/profile";
    }

    @GetMapping
    public String getAllActiveResumes(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page,
                                      @RequestParam(name = "category", defaultValue = "default") String category) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (category.equalsIgnoreCase("выбрать категорию (все)")) {
            category = "default";
        }
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth).getAccountType());
            model.addAttribute("type", accountType);
        }

        Page<ResumeViewAllDto> resumesPage = resumeService.getAllActiveResumes(page, 5, category);
        model.addAttribute("resumesPage", resumesPage);
        model.addAttribute("category", category);
        return "resumes/all_resumes";
    }




    @GetMapping("/{resumeId}")
    public String getResume(@PathVariable Integer resumeId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth).getAccountType());
            model.addAttribute("type", accountType);
        }

        model.addAttribute("resume", resumeService.getResumeById(resumeId));
        return "resumes/resume_info";
    }

    @GetMapping("/create")
    public String createResume(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth).getAccountType());
            model.addAttribute("type", accountType);
        }
        model.addAttribute("resumeCreateDto", new ResumeCreateDto());
        return "resumes/create_resume";
    }

    @PostMapping("/create")
    public String createResume(@Valid ResumeCreateDto resumeDto, BindingResult bindingResult, Model model, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName().equals("anonymousUser")) {
                model.addAttribute("username", null);
            } else {
                model.addAttribute("username", auth.getName());
                AccountType accountType = AccountType.valueOf(getUserFromAuth(auth).getAccountType());
                model.addAttribute("type", accountType);
            }
            model.addAttribute("resumeCreateDto", resumeDto);
            return "resumes/create_resume";
        }
        resumeService.createResumeForApplicant(resumeDto, authentication);
        return "redirect:/profile";
    }

    @PostMapping("/delete/{resumeId}")
    public String deleteVacancy(@PathVariable Integer resumeId, Authentication authentication) {
        resumeService.deleteResumeById(resumeId, authentication);
        return "redirect:/profile";
    }

    @SneakyThrows
    public User getUserFromAuth(Authentication auth) {
        return userRepository.findByEmail(auth.getName()).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
