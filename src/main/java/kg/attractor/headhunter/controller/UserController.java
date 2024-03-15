package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/id{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("users/name{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        try {
            List<UserDto> users = userService.getUserByName(name);
            return ResponseEntity.ok(users);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("users/phoneNumber{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            List<UserDto> users = userService.getUserByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(users);
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

    @GetMapping("users/existByEmail{email}")
    public ResponseEntity<Boolean> checkUserExistenceByEmail(@PathVariable String email) {
        boolean exists = userService.doesUserExistByEmail(email);
        return ResponseEntity.ok(exists);
    }


    @PostMapping("users/edit")
    public ResponseEntity<?> editUser(@RequestBody UserDto userDto) {
        userService.editUser(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("users/delete{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}