package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.ApplicantDto;
import kg.attractor.headhunter.dto.EmployerDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.ProfileService;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserDao userDao;

    @SneakyThrows
    @Override
    public Object getUserProfile(Integer id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user!"));

        if (user.getAccountType().equals(AccountType.APPLICANT)) {
            return getApplicantById(user.getId()).get();

        } else if (user.getAccountType().equals(AccountType.EMPLOYER)) {
            return getEmployerByUserId(user.getId()).get();

        } else throw new NoSuchElementException("User type is not found");
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> maybeUser = userDao.getUserById(id);
        if (maybeUser.isPresent()) return maybeUser.get();
        else throw new NoSuchElementException("User does not exist");
    }

    @Override
    @SneakyThrows
    public List<?> getProfileContent(Integer id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("Can't find user!"));
        ;
        if (user.getAccountType().equals(AccountType.APPLICANT)) {
            return resumeService.getAllResumesOfApplicantById(id);
        } else if (user.getAccountType().equals(AccountType.EMPLOYER)) {
            return vacancyService.getAllVacanciesOfEmployerById(id);
        } else throw new NoSuchElementException("User type is not found");
    }

    public Optional<ApplicantDto> getApplicantById(Integer id) {
        var a = userDao.getUserById(id);
        if (a.isPresent()) return Optional.of(makeDtoFromApplicant(a.get()));
        else throw new NoSuchElementException("Applicant profile not found");
    }

    public Optional<EmployerDto> getEmployerByUserId(Integer id) {
        var e = userDao.getUserById(id);
        if (e.isPresent()) return Optional.of(makeDtoFromEmployer(e.get()));
        else throw new NoSuchElementException("Employer profile does not exist");
    }

    private EmployerDto makeDtoFromEmployer(User user) {
        EmployerDto e = new EmployerDto();
        e.setId(user.getId());
        e.setCompanyName(user.getName());
        return e;
    }

    private ApplicantDto makeDtoFromApplicant(User user) {
        ApplicantDto a = new ApplicantDto();

        a.setId(user.getId());
        a.setName(user.getName());
        a.setSurname((user.getSurname()));
        a.setAge(user.getAge());
        return a;
    }
}
