package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.dto.UserPasswordChangeDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.ProfileService;
import kg.attractor.headhunter.service.RespondedApplicantService;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final RespondedApplicantService respondedApplicantService;
    private final UserRepository userRepository;

    @GetMapping
    public String profile(Model user, Model content, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            content.addAttribute("username", null);
        } else {
            content.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            content.addAttribute("type", accountType);
        }
        user.addAttribute("user", profileService.getUserDto(authentication));
        content.addAttribute("items", profileService.getProfileContent(authentication));
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editProfile(Model model, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        UserDto user = userService.getUserByAuth(authentication);
        model.addAttribute("user", user);
        return "profile/edit_profile";
    }

    @PostMapping("/edit")
    public String editProfile(@Valid UserEditDto user, Authentication authentication, Model model) {
        userService.editUser(user, authentication);
        return "redirect:/profile";
    }

    @PostMapping("/edit/password")
    public String editProfilePassword(@Valid UserPasswordChangeDto user, Authentication authentication) {
        userService.editUserPassword(user, authentication);
        return "redirect:/profile";
    }

    @GetMapping("/avatars")
    public ResponseEntity<?> getAvatar(Authentication authentication) {
        return userService.getPhoto(authentication);
    }

    @GetMapping("/avatars/{id}")
    public ResponseEntity<?> getAvatarById(@PathVariable Integer id) {
        return userService.getPhotoById(id);
    }

    @PostMapping("/avatars")
    public String uploadAvatar(@RequestBody MultipartFile file, Authentication authentication) {
        userService.uploadUserAvatar(authentication, file);
        return "redirect:/profile/edit";
    }

    @GetMapping("/repliedToVacancies")
    public String getRepliedToVacanciesForApplicant(Authentication authentication, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        model.addAttribute("items", respondedApplicantService.getRespondedApplicantDtoForChatByUserId(authentication));
        return "applying/applicantsResponds";
    }

    @GetMapping("/getRespondsToVacancies")
    public String getRespondsToVacanciesForEmployer(Authentication authentication, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        model.addAttribute("items", respondedApplicantService.getRespondedApplicantDtoForChatByUserId(authentication));
        return "applying/respondsToVacancies";
    }


    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
