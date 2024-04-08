package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.service.ProfileService;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/register")
    public String register() {
        return "authentication/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserCreateDto userDto) {
        userService.createUser(userDto);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login() {
        return "authentication/login";
    }

    @GetMapping("users/{id}")
    public String profile(@PathVariable Integer id, Model profile, Model user, Model content) {
        profile.addAttribute("profile", profileService.getUserProfile(id));
        user.addAttribute("user", profileService.getUserById(id));
        content.addAttribute("items", profileService.getProfileContent(id));
        return "users/profile";
    }


    @GetMapping("users/edit/{userId}")
    public String editProfile(@PathVariable Integer userId, Model model) {
        UserDto user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "users/edit_user";
    }

    @PostMapping("users/edit/{userId}")
    public String editProfile(@Valid UserEditDto user, @PathVariable Integer userId) {
        userService.editUserById(user, userId);
        return "redirect:/users/" + userId;
    }

    @GetMapping("users/avatars/{id}")
    public ResponseEntity<?> getAvatarById(@PathVariable Integer id) {
        return userService.getPhoto(id);
    }
    @PostMapping("/users/avatars/{id}")
    @ResponseStatus(code = HttpStatus.SEE_OTHER)
    public String uploadAvatar(@PathVariable Integer id, @RequestBody MultipartFile file) {
        userService.uploadUserAvatar(id, file);
        return "redirect:/users/edit/" + id;
    }
}
