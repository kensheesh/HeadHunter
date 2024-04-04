package kg.attractor.headhunter.controller.api;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor

public class UserRestController {
    private final UserService userService;

    @GetMapping("/applicants")
    public ResponseEntity<List<UserDto>> getAllApplicants() {
        return ResponseEntity.ok(userService.getAllApplicants());
    }

    @GetMapping("/employers")
    public ResponseEntity<List<UserDto>> getAllEmployers() {
        return ResponseEntity.ok(userService.getAllEmployers());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "id", required = false) Integer id,
                                    @RequestParam(name = "employerName", required = false) String employerName,
                                    @RequestParam(name = "applicantName", required = false) String applicantName,
                                    @RequestParam(name = "applicantSurname", required = false) String applicantSurname,
                                    @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                    @RequestParam(name = "email", required = false) String email) {
        if (id != null) {
            return ResponseEntity.ok(userService.getUserById(id));
        } else if (employerName != null) {
            return ResponseEntity.ok(userService.getEmployersByName(employerName));
        } else if (applicantName != null) {
            return ResponseEntity.ok(userService.getApplicantsByName(applicantName));
        } else if (applicantSurname != null) {
            return ResponseEntity.ok(userService.getApplicantsBySurname(applicantSurname));
        } else if (phoneNumber != null) {
            return ResponseEntity.ok(userService.getApplicantByPhoneNumber(phoneNumber));
        } else if (email != null) {
            return ResponseEntity.ok(userService.getApplicantByEmail(email));
        } else {
            return ResponseEntity.ok().body("Nullable input");
        }
    }

    @PostMapping("api")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto user, Authentication authentication) {
        userService.createUser(user);
        return ResponseEntity.ok("User is created");
    }

    @PutMapping
    public ResponseEntity<?> editUser(@Valid @RequestBody UserEditDto user, Authentication authentication) {
        userService.editUser(user, authentication);
        return ResponseEntity.ok("User is edited");
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> addAvatar(@RequestParam(value = "file", required = true) MultipartFile file, Authentication authentication) {
        userService.addAvatar(file, authentication);
        return ResponseEntity.ok("Avatar is added");
    }
}
