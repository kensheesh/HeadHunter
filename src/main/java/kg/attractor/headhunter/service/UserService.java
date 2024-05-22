package kg.attractor.headhunter.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.dto.UserPasswordChangeDto;
import kg.attractor.headhunter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

public interface UserService {
    void login(Authentication auth);

    ResponseEntity<?> getPhoto(Authentication authentication);

    ResponseEntity<?> getPhotoById(Integer id);

    Page<UserDto> getAllApplicants(Pageable pageable);

    Page<UserDto> getAllEmployers(Pageable pageable);

    UserDto getUserById(Integer id);

    UserDto getUserByAuth(Authentication authentication);

    void createUser(UserCreateDto userCreateDto);

    void editUser(UserEditDto userEditDto, Authentication authentication);

    void uploadUserAvatar(Authentication authentication, MultipartFile file);

    void editUserPassword(UserPasswordChangeDto user, Authentication authentication);

    void updateResetPasswordToken(String token, String email);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

    void makeResetPasswdLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;
}
