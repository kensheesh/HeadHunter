package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserByName(String name) throws UserNotFoundException;

    UserDto getUserByPhoneNumber(String phoneNumber) throws UserNotFoundException;

    UserDto getUserByEmail(String email) throws UserNotFoundException;

    UserDto checkUserInDB(String email) throws UserNotFoundException;
}
