package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.dto.UserPasswordChangeDto;
import kg.attractor.headhunter.service.ProfileService;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String profile(Model user, Model content, Authentication authentication) {
        user.addAttribute("user", profileService.getUserDto(authentication));
        content.addAttribute("items", profileService.getProfileContent(authentication));
        System.out.println(passwordEncoder.encode("Test1!"));
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editProfile(Model model, Authentication authentication) {
        UserDto user = userService.getUserByAuth(authentication);
        model.addAttribute("user", user);
        return "profile/edit_profile";
    }

    @PostMapping("/edit")
    public String editProfile(@Valid UserEditDto user, Authentication authentication) {
        userService.editUser(user, authentication);
        return "redirect:/profile";
    }

    @PostMapping("/edit/password")
    public String editProfilePassword(@Valid UserPasswordChangeDto user, Authentication authentication) {
        userService.editUserPassword(user, authentication);
        return "redirect:/profile";
    }

    @GetMapping("/avatars")
    public ResponseEntity<?> getAvatarById(Authentication authentication) {
        return userService.getPhoto(authentication);
    }
    @PostMapping("/avatars")
    public String uploadAvatar(@RequestBody MultipartFile file, Authentication authentication) {
        userService.uploadUserAvatar(authentication, file);
        return "redirect:/profile/edit";
    }
}
