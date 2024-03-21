package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(int id) throws UserNotFoundException;

    List<UserDto> getUsersByName(String name, int UserId) throws UserNotFoundException;

    List<UserDto> getUsersBySurname(String surname, int userId) throws UserNotFoundException;

    List<UserDto> getUserByPhoneNumber(String phoneNumber, int userId) throws UserNotFoundException;


    UserDto getUserByEmail(String email, int userId) throws UserNotFoundException;

    boolean doesUserExistByEmail(String email);

    void editUser(UserDto userDto);

    void addAvatar(int id, MultipartFile file) throws UserNotFoundException;

    void createUser(UserDto userDto);
}
