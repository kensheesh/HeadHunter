package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.ProfileService;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final ModelMapper modelMapper;
    private final UserDao userDao;

    @SneakyThrows
    @Override
    public UserDto getUserDto(Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    @SneakyThrows
    public List<?> getProfileContent(Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        if (user.getAccountType().equals(AccountType.APPLICANT)) {
            return resumeService.getAllResumesOfApplicantById(user.getId());
        } else if (user.getAccountType().equals(AccountType.EMPLOYER)) {
            return vacancyService.getAllVacanciesOfEmployerById(user.getId());
        } else throw new NoSuchElementException("User type is not found");
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
