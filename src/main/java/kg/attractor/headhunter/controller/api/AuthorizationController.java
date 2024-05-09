package kg.attractor.headhunter.controller.api;

import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;

    @PostMapping("login")
    public HttpStatus login(Authentication auth) {
        userService.login(auth);
        return HttpStatus.OK;
    }

}
