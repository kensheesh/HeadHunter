package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.*;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {
    private final ResumeService resumeService;
    private final UserDao userDao;


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
            AccountType accountType = getUserFromAuth(auth.getPrincipal().toString()).getAccountType();
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
            AccountType accountType = getUserFromAuth(auth.getPrincipal().toString()).getAccountType();
            model.addAttribute("type", accountType);
        }

        model.addAttribute("resume", resumeService.getResumeById(resumeId));
        return "resumes/resume_info";
    }

    @GetMapping("/create")
    public String editResume() {
        return "resumes/create_resume";
    }

    @PostMapping("/create")
    public String createResume(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "categoryName") String categoryName,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            @RequestParam(name = "salary") BigDecimal salary,

            @RequestParam(name = "years", required = false) List<Integer> years,
            @RequestParam(name = "companyName", required = false) List<String> companyNames,
            @RequestParam(name = "position", required = false) List<String> positions,
            @RequestParam(name = "responsibilities", required = false) List<String> responsibilities,

            @RequestParam(name = "institution", required = false) List<String> institutions,
            @RequestParam(name = "program", required = false) List<String> programs,
            @RequestParam(name = "startDate", required = false) List<LocalDate> startDates,
            @RequestParam(name = "endDate", required = false) List<LocalDate> endDates,
            @RequestParam(name = "degree", required = false) List<String> degrees,

            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "telegram", required = false) String telegram,
            @RequestParam(name = "linkedin", required = false) String linkedin,
            @RequestParam(name = "facebook", required = false) String facebook
    ) {

        ResumeCreateDto resumeDto = ResumeCreateDto.builder()
                .name(name)
                .categoryName(categoryName)
                .isActive(isActive)
                .salary(salary)
                .build();

        if (years != null) {
            for (int i = 0; i < years.size(); i++) {
                WorkExperienceInfoDto workExperience = WorkExperienceInfoDto.builder()
                        .years(years.get(i))
                        .companyName(companyNames.get(i))
                        .position(positions.get(i))
                        .responsibilities(responsibilities.get(i))
                        .build();
                resumeDto.getWorkExpInfos().add(workExperience);
            }
        }

        if (institutions != null) {
            for (int i = 0; i < institutions.size(); i++) {
                EducationInfoDto educationInfo = EducationInfoDto.builder()
                        .institution(institutions.get(i))
                        .program(programs.get(i))
                        .startDate(startDates.get(i))
                        .endDate(endDates.get(i))
                        .degree(degrees.get(i))
                        .build();
                resumeDto.getEducationInfos().add(educationInfo);
            }
        }

        List<ContactInfoDto> contacts = new ArrayList<>();

        if (phone != null && !phone.isEmpty()) {
            contacts.add(ContactInfoDto.builder()
                    .contactType("PhoneNumber")
                    .value(phone)
                    .build());
        }
        if (email != null && !email.isEmpty()) {
            contacts.add(ContactInfoDto.builder()
                    .contactType("Email")
                    .value(email)
                    .build());
        }
        if (telegram != null && !telegram.isEmpty()) {
            contacts.add(ContactInfoDto.builder()
                    .contactType("Telegram")
                    .value(telegram)
                    .build());
        }
        if (linkedin != null && !linkedin.isEmpty()) {
            contacts.add(ContactInfoDto.builder()
                    .contactType("LinkedIn")
                    .value(linkedin)
                    .build());
        }
        if (facebook != null && !facebook.isEmpty()) {
            contacts.add(ContactInfoDto.builder()
                    .contactType("Facebook")
                    .value(facebook)
                    .build());
        }

        resumeDto.setContactInfos(contacts);

        System.out.println(resumeDto.getName());
        System.out.println(resumeDto.getCategoryName());
        System.out.println(resumeDto.getIsActive());
        System.out.println(resumeDto.getSalary());

        System.out.println(resumeDto.getEducationInfos());
        System.out.println(resumeDto.getWorkExpInfos());

        resumeService.createResumeForApplicant(resumeDto);

        return "redirect:/resumes";
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
