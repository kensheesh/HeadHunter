package kg.attractor.headhunter.controller.mvc;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "authentication/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserCreateDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCreateDto", userDto);
            return "authentication/register";
        }
        userService.createUser(userDto);
        autoLogin(request, userDto.getEmail(), userDto.getPassword());
        return "redirect:/profile";
    }

    public void autoLogin(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login ", e);
        }
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null) {
            User user = getUserFromAuth(authentication);
            if (user != null) {
                if (user.getAccountType().equals("EMPLOYER")) {
                    return "redirect:/resumes";
                } else {
                    return "redirect:/vacancies";
                }
            }
        }
        return "authentication/login";
    }

    @SneakyThrows
    public User getUserFromAuth(Authentication auth) {
        return userRepository.findByEmail(auth.getName()).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }

    @GetMapping("forgot_password")
    public String showForgotPasswordForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth).getAccountType());
            model.addAttribute("type", accountType);
        }
        return "authentication/forgot_password_form";
    }


    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswdLink(request);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (MessagingException ex) {
            model.addAttribute("error", "Error while sending email");
        }
        return "authentication/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "Invalid token");
        }
        return "authentication/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("message", "Invalid Token");
        }
        return "message";
    }
}