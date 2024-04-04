package kg.attractor.headhunter.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("users/register")
    public String register() {
        return "register";
    }

    @PostMapping("users/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@Valid UserCreateDto userDto) throws Exception {
        userService.createUser(userDto);
        return "redirect:/";
    }

}
