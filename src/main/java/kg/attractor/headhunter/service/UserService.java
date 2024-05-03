package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.dto.UserPasswordChangeDto;
import kg.attractor.headhunter.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    ResponseEntity<?> getPhoto(Authentication authentication);
    ResponseEntity<?> getPhotoById(Integer id);

//    List<UserDto> getAllApplicants();
//
//    List<UserDto> getAllEmployers();

    UserDto getUserById(Integer id);

    UserDto getUserByAuth(Authentication authentication);

    UserDto getUserByEmail(String email);

//    List<UserDto> getEmployersByName(String name);
//
//    List<UserDto> getApplicantsByName(String name);
//
//    List<UserDto> getApplicantsBySurname(String surname);

//    UserDto getApplicantByEmail(String email);
//
//    UserDto getApplicantByPhoneNumber(String phoneNumber);

    void createUser(UserCreateDto userCreateDto);

    void editUser(UserEditDto userEditDto, Authentication authentication);

//    void editUserById(UserEditDto userEditDto, Integer userId);
//
//    void addAvatar(MultipartFile file, Authentication authentication);

    void uploadUserAvatar(Authentication authentication, MultipartFile file);

//    UserDto mapToUserDto(User user);

    void editUserPassword(UserPasswordChangeDto user, Authentication authentication);
}
