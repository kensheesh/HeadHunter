package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByName(String name, int userId) throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        if (userDao.getUserById(userId).get().getAccountType().name().equals("EMPLOYER")) {
            users = userDao.getApplicantsByName(name);
        }
        if (userDao.getUserById(userId).get().getAccountType().name().equals("APPLICANT")) {
            users = userDao.getEmployersByName(name);
        }

        if (users.isEmpty()) {
            throw new UserNotFoundException("Can't find user with name: " + name);
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersBySurname(String surname, int userId) throws UserNotFoundException {
        List<User> users = userDao.getApplicantsBySurname(surname);
        if (userId > userDao.getUsers().size() || userId < 1) {
            throw new UserNotFoundException("Don't have access");
        }

        Optional<User> userForChecking = userDao.getUserById(userId);
        if (userForChecking.isPresent()) {
            if (userForChecking.get().getAccountType().name().equals("APPLICANT")) {
                throw new UserNotFoundException("Don't have access");
            }
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> getUserByPhoneNumber(String phoneNumber, int userId) throws UserNotFoundException {
        if (userId > userDao.getUsers().size() || userId < 1) {
            throw new UserNotFoundException("Don't have access");
        }

        Optional<User> userForChecking = userDao.getUserById(userId);
        if (userForChecking.isPresent()) {
            if (userForChecking.get().getAccountType().name().equals("APPLICANT")) {
                throw new UserNotFoundException("Don't have access");
            }
        }

        List<User> users = userDao.getUserByPhoneNumber(phoneNumber);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Can't find user with phoneNumber:" + phoneNumber);
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUserByEmail(String email, int userId) throws UserNotFoundException {
        if (userId > userDao.getUsers().size() || userId < 1) {
            throw new UserNotFoundException("Don't have access");
        }

        Optional<User> userForChecking = userDao.getUserById(userId);
        if (userForChecking.isPresent()) {
            if (userForChecking.get().getAccountType().name().equals("APPLICANT")) {
                throw new UserNotFoundException("Don't have access");
            }
        }

        User user = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("Can't find user with email:" + email));
        return modelMapper.map(user, UserDto.class);
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
    public void createUser(UserDto userDto) {
        log.info("Creating new user: {}", userDto.getName());
        if (userDto.getAccountType().name().equals("EMPLOYER")) {
            userDto.setSurname(null);
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAvatar("base_avatar.jpg");
        user.setAccountType(userDto.getAccountType());
        userDao.createUser(user);
    }

    @Override
    public void addAvatar(int id, MultipartFile file) throws UserNotFoundException {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
        String filename = fileUtil.saveUploadedFile(file, "avatars");
        user.setAvatar(filename);
        userDao.addAvatar(user);
    }
}
