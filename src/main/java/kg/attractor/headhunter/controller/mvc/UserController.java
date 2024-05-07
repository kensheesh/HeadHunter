package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/employers")
    public String getEmployers(Model model,
                               @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        Pageable pageable = PageRequest.of(page, 3);
        model.addAttribute("users", userService.getAllEmployers(pageable));
        model.addAttribute("userType", AccountType.EMPLOYER);

        return "users/users";
    }

    @GetMapping("/applicants")
    public String getApplicants(Model model,
                                @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        Pageable pageable = PageRequest.of(page, 3);
        model.addAttribute("users",userService.getAllApplicants(pageable));
        model.addAttribute("userType", AccountType.APPLICANT);
        return "users/users";
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
