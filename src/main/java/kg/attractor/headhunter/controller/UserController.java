package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/name{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        try {
            UserDto user = userService.getUserByName(name);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("users/phoneNumber{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            UserDto user = userService.getUserByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("users/email{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            UserDto user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("users/existByEmail/{email}")
    public ResponseEntity<Boolean> checkUserExistenceByEmail(@PathVariable String email) {
        boolean exists = userService.doesUserExistByEmail(email);
        return ResponseEntity.ok(exists);
    }

}