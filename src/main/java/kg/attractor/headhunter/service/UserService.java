package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserById(int id) throws UserNotFoundException;
    List<UserDto> getUserByName(String name) throws UserNotFoundException;

    List<UserDto> getUserByPhoneNumber(String phoneNumber) throws UserNotFoundException;

    UserDto getUserByEmail(String email) throws UserNotFoundException;
    boolean doesUserExistByEmail(String email);

    void editUser(UserDto userDto);
    void deleteUserById(int id);
    void addAvatar(int id, MultipartFile file) throws UserNotFoundException;

}
