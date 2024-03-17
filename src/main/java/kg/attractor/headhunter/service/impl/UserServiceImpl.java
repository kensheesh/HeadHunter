package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil fileUtil;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(UserDto.builder()
                .id(e.getId())
                .name(e.getName())
                .surname(e.getSurname())
                .age(e.getAge())
                .email(e.getEmail())
                .password(e.getPassword())
                .phoneNumber(e.getPhoneNumber())
                .avatar(e.getAvatar())
                .accountType(e.getAccountType())
                .build()));
        return dtos;
    }

    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public List<UserDto> getUserByName(String name) throws UserNotFoundException{
        List<User> users = userDao.getUserByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Can't find user with name: " + name);
        }
        return users.stream().map(user -> UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build()).collect(Collectors.toList());
    }



    public List<UserDto> getUserByPhoneNumber(String phoneNumber) throws UserNotFoundException {
        List<User> users = userDao.getUserByPhoneNumber(phoneNumber);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Can't find user with phoneNumber:" + phoneNumber);
        }
        return users.stream().map(user -> UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build()).collect(Collectors.toList());
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        User user = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("Can't find user with email:" + email));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public boolean doesUserExistByEmail(String email) {
        return userDao.doesUserExistByEmail(email);
    }

    @Override
    public void editUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setPassword(userDto.getPassword());
        user.setAvatar(userDto.getAvatar());

        userDao.editUser(user);
    }


    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void addAvatar(int id, MultipartFile file) throws UserNotFoundException {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
        String filename = fileUtil.saveUploadedFile(file, "avatars");
        user.setAvatar(filename);
        userDao.addAvatar(user);
    }

}
