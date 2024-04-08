package kg.attractor.headhunter.service.impl;

import com.sun.security.auth.UnixNumericGroupPrincipal;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @SneakyThrows
    @Override
    public ResponseEntity<?> getPhoto(Integer id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!((user.getAvatar() == null) && !user.getAvatar().isEmpty())) {
            String extension = getFilePath(user.getAvatar());
            if (extension != null && extension.equalsIgnoreCase("png")) {
                return fileUtil.getOutputFile(user.getAvatar(), "avatars", MediaType.IMAGE_PNG);
            } else if (extension != null && extension.equalsIgnoreCase("jpeg")) {
                return fileUtil.getOutputFile(user.getAvatar(), "avatars", MediaType.IMAGE_JPEG);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    private String getFilePath(String avatar) {
        int x = avatar.lastIndexOf(".");
        if (x != -1 && x < avatar.length() - 1) {
            return avatar.substring(x + 1);
        }
        return null;
    }


    @Override
    @SneakyThrows
    public List<UserDto> getAllApplicants() {
        List<User> users = userDao.getAllApplicants();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Cannot find any applicants");
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public List<UserDto> getAllEmployers() {
        List<User> users = userDao.getAllEmployers();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Cannot find any employers");
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    @SneakyThrows
//    public UserDto search(Integer id, String name, String surname, String phoneNumber, String email) {
//        if (id != null) {
//            User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
//            return modelMapper.map(user, UserDto.class);
//        } else if (name != null) {
//            return ResponseEntity.ok(userService.getApplicantsBySurname(surname));
//        } else if (email != null) {
//            return ResponseEntity.ok(List.of(userService.getApplicantByEmail(email)));
//        } else if (phoneNumber != null) {
//            return ResponseEntity.ok(List.of(userService.getApplicantByPhoneNumber(phoneNumber)));
//        }
//        // Если никакие параметры не установлены или не соответствуют условиям выше, верните всех пользователей
//        // Это может быть не лучшим решением с точки зрения производительности, нужно решить, как лучше обрабатывать такие случаи
//        return ResponseEntity.ok(userService.getAllApplicants()); // Или другой подходящий метод
//
//    }

    @Override
    @SneakyThrows
    public UserDto getUserById(Integer id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @SneakyThrows
    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + email));
        return modelMapper.map(user, UserDto.class);
    }


    @Override
    @SneakyThrows
    public List<UserDto> getEmployersByName(String name) {
        List<User> users = userDao.getEmployersByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Cannot find any employers with name " + name);
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public List<UserDto> getApplicantsByName(String name) {
        List<User> users = userDao.getApplicantsByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Cannot find any applicants with name " + name);
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public List<UserDto> getApplicantsBySurname(String surname) {
        List<User> users = userDao.getApplicantsBySurname(surname);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Cannot find any applicants with name " + surname);
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public UserDto getApplicantByEmail(String email) {
        User user = userDao.getApplicantByEmail(email).orElseThrow(() -> new UserNotFoundException("Can't find applicant with email: " + email));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @SneakyThrows
    public UserDto getApplicantByPhoneNumber(String phoneNumber) {
        User user = userDao.getApplicantByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("Can't find applicant with phone number: " + phoneNumber));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @SneakyThrows
    public void createUser(UserCreateDto userCreateDto) {
        // Проверка существования пользователя по email
        if (userDao.isExists(userCreateDto.getEmail())) {
            throw new RuntimeException("User with email " + userCreateDto.getEmail() + " already exists");
        }

        // Проверка существования пользователя по номеру телефона
        userDao.getUserByPhoneNumber(userCreateDto.getPhoneNumber())
                .ifPresent(user -> {
                    throw new RuntimeException("User with phone number " + userCreateDto.getPhoneNumber() + " already exists");
                });

        if (userCreateDto.getAccountType().equals(AccountType.APPLICANT) && userCreateDto.getSurname() == null) {
            throw new UserNotFoundException("Applicant must have surname");
        }

        User user = new User();
        user.setName(userCreateDto.getName());
        user.setSurname(userCreateDto.getAccountType().equals(AccountType.APPLICANT) ? userCreateDto.getSurname() : null);
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setAccountType(userCreateDto.getAccountType());
        user.setRoleId(userDao.getRoleIdByAccountType(userCreateDto.getAccountType()));
        userDao.createUser(user);
    }

    @Override
    @SneakyThrows
    public void editUser(UserEditDto userEditDto, Authentication authentication) {
        User mayBeUser = getUserFromAuth(authentication.getPrincipal().toString());
        userDao.getUserByEmail(mayBeUser.getEmail()).orElseThrow(() -> new UserNotFoundException("Unexpected problem with authentication"));
        User user = userDao.getUserByEmail(mayBeUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (userEditDto.getName() != null && !userEditDto.getName().isBlank()) {
            user.setName(userEditDto.getName());
        } else if (userEditDto.getName() != null) {
            throw new UserNotFoundException("Name cannot contain blanks");
        }
        user.setSurname(mayBeUser.getAccountType().equals(AccountType.APPLICANT) ? userEditDto.getSurname() : null);
        user.setAge(userEditDto.getAge());
        if (userEditDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        }

        userDao.editUser(user);
    }

    @Override
    @SneakyThrows
    public void editUserById(UserEditDto userEditDto, Integer userId) {
        User mayBeUser = userDao.getUserById(userId).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + userId));

        userDao.getUserByEmail(mayBeUser.getEmail()).orElseThrow(() -> new UserNotFoundException("Unexpected problem with authentication"));
        User user = userDao.getUserByEmail(mayBeUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));


        if (userEditDto.getName() != null && !userEditDto.getName().isBlank()) {
            user.setName(userEditDto.getName());
        } else if (userEditDto.getName() != null) {
            throw new UserNotFoundException("Name cannot contain blanks");
        }
        user.setSurname(mayBeUser.getAccountType().equals(AccountType.APPLICANT) ? userEditDto.getSurname() : null);
        user.setAge(userEditDto.getAge());
        if (userEditDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        }
        userDao.editUser(user);
    }

    @Override
    @SneakyThrows
    public void addAvatar(MultipartFile file, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        userDao.getUserByEmail(user.getEmail()).orElseThrow(() -> new UserNotFoundException("Can't find user with this email"));

        String filename = fileUtil.saveUploadedFile(file, "avatars");
        user.setAvatar(filename);
        userDao.addAvatar(user);
    }

    @Override
    @SneakyThrows
    public void uploadUserAvatar(Integer id, MultipartFile file) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        String fileName = fileUtil.saveUploadedFile(file, "avatars");
        user.setAvatar(fileName);
        userDao.addAvatar(user);
    }


    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }

}
