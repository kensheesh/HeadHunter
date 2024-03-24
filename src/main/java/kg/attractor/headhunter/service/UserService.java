package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDto> getAllApplicants();

    List<UserDto> getAllEmployers();

    UserDto getUserById(Integer id);
    UserDto getUserByEmail(String email);

    List<UserDto> getEmployersByName(String name);

    List<UserDto> getApplicantsByName(String name);

    List<UserDto> getApplicantsBySurname(String surname);

    UserDto getApplicantByEmail(String email);

    UserDto getApplicantByPhoneNumber(String phoneNumber);

    void createUser(UserCreateDto userCreateDto, Authentication authentication);
    void editUser(UserEditDto userEditDto, Authentication authentication);

}
