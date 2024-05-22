package kg.attractor.headhunter.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.headhunter.dto.UserCreateDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.UserEditDto;
import kg.attractor.headhunter.dto.UserPasswordChangeDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.util.FileUtil;
import kg.attractor.headhunter.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @Override
    public void login(Authentication auth) {
        log.info(auth.getName());
    }

    @Override
    public Page<UserDto> getAllApplicants(Pageable pageable) {
        return userRepository.findByAccountType(AccountType.APPLICANT.toString(), pageable)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Page<UserDto> getAllEmployers(Pageable pageable) {
        return userRepository.findByAccountType(AccountType.EMPLOYER.toString(), pageable)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<?> getPhoto(Authentication authentication) {
        User user = getUserFromAuth(authentication);
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

    @SneakyThrows
    @Override
    public ResponseEntity<?> getPhotoById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
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
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can't find user with id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @SneakyThrows
    public UserDto getUserByAuth(Authentication authentication) {
        User user = getUserFromAuth(authentication);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @SneakyThrows
    public void createUser(UserCreateDto userCreateDto) {
        if (userRepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            throw new UserNotFoundException("User with email " + userCreateDto.getEmail() + " already exists");
        }

        userRepository.findByPhoneNumber(userCreateDto.getPhoneNumber())
                .ifPresent(user -> {
                    throw new UsernameNotFoundException("User with phone number " + userCreateDto.getPhoneNumber() + " already exists");
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
        user.setAvatar("base_avatar.png");
        user.setEnabled(true);
        user.setAccountType(userCreateDto.getAccountType().toString());
        int roleId;
        if (userCreateDto.getAccountType().equals(AccountType.EMPLOYER)) {
            roleId = 1;
        } else {
            roleId = 2;
        }
        user.setRoleId(roleId);
        userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public void editUser(UserEditDto userEditDto, Authentication authentication) {
        User mayBeUser = getUserFromAuth(authentication);
        userRepository.findByEmail(mayBeUser.getEmail()).orElseThrow(() -> new UserNotFoundException("Unexpected problem with authentication"));
        User user = userRepository.findByEmail(mayBeUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (userEditDto.getName() != null && !userEditDto.getName().isBlank()) {
            user.setName(userEditDto.getName());
        } else if (userEditDto.getName() != null) {
            throw new UserNotFoundException("Name cannot contain blanks");
        }
        user.setSurname(mayBeUser.getAccountType().equals("APPLICANT") ? userEditDto.getSurname() : null);
        user.setAge(userEditDto.getAge());
        if (userEditDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        }

        userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public void uploadUserAvatar(Authentication authentication, MultipartFile file) {
        User user = getUserFromAuth(authentication);

        String fileName = fileUtil.saveUploadedFile(file, "avatars");
        user.setAvatar(fileName);
        userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void editUserPassword(UserPasswordChangeDto userDto, Authentication authentication) {
        User user = getUserFromAuth(authentication);

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new UserNotFoundException("Passwords aren't the same!");
        }

        String password = passwordEncoder.encode(userDto.getPassword());

        user.setPassword(password);
        userRepository.save(user);
    }


    @SneakyThrows
    public User getUserFromAuth(Authentication auth) {
        return userRepository.findByEmail(auth.getName()).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }


    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Could not find any user with the email " + email));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void makeResetPasswdLink(HttpServletRequest request) throws UsernameNotFoundException, UnsupportedEncodingException, MessagingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }
}