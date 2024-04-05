package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.service.ProfileService;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("users/register")
    public String register() {
        return "register";
    }

    @PostMapping("users/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@Valid UserCreateDto userDto) throws Exception {
        userService.createUser(userDto);
        return "redirect:/users/register";
    }

    @GetMapping("users/{id}")
    public String profile(@PathVariable Integer id, Model profile, Model user, Model content) throws Exception {
        profile.addAttribute("profile", profileService.getUserProfile(id));
        user.addAttribute("user", profileService.getUserById(id));
        content.addAttribute("items", profileService.getProfileContent(id));
        return "profile";
    }



    @PostMapping("users/edit/{userId}")
    public String editProfile(@Valid UserEditDto user, @PathVariable Integer userId, Model model) {
        userService.editUserById(user, userId);
        return "redirect:/users/" + userId;
    }

    @GetMapping("users/edit/{userId}")
    public String editProfile(@PathVariable Integer userId, Model model) {
        UserDto user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "edit";
    }

}
