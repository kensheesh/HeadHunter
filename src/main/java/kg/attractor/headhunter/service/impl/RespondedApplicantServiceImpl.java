package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.RespondedApplicantDao;
import kg.attractor.headhunter.dto.RespondToVacancyDto;
import kg.attractor.headhunter.dto.RespondedApplicantDtoForChat;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.dto.VacancyForRespondedDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.model.*;
import kg.attractor.headhunter.repository.*;
import kg.attractor.headhunter.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;
    //    private final UserDao userDao;
//    private final VacancyDao vacancyDao;
//    private final ResumeDao resumeDao;
//    private final CategoryDao categoryDao;
//    private final MessageDao messageDao;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public void createRespondedApplicant(RespondToVacancyDto respondToVacancyDto, Authentication authentication) {
        User currentUser = getUserFromAuth(authentication.getPrincipal().toString());

        Resume resumeFor = resumeRepository.findById(respondToVacancyDto.getResumeId()).orElseThrow();
        Vacancy vacancyFor = vacancyRepository.findById(respondToVacancyDto.getVacancyId()).orElseThrow();
        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setResume(resumeFor);
        respondedApplicant.setVacancy(vacancyFor);
        respondedApplicant.setConfirmation(false);
        RespondedApplicant respondedApplicantCreated = respondedApplicantRepository.save(respondedApplicant);

        Vacancy vacancy = vacancyRepository.findById(respondToVacancyDto.getVacancyId()).orElseThrow();

        Resume resume = resumeRepository.findById(respondToVacancyDto.getResumeId()).orElseThrow();

        Message messageBasic = new Message();
        messageBasic.setUserFrom(currentUser);
        messageBasic.setUserTo(vacancy.getAuthor());
        messageBasic.setRespondedApplicant(respondedApplicantCreated);
        messageBasic.setContent("/resumes/" + resume.getId());
        messageBasic.setTimestamp(LocalDateTime.now());
        messageRepository.save(messageBasic);

        Message message = new Message();
        message.setUserFrom(currentUser);
        message.setUserTo(vacancy.getAuthor());
        message.setRespondedApplicant(respondedApplicantCreated);
        message.setContent(respondToVacancyDto.getMessage());
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
    }


    @Override
    @SneakyThrows
    public List<VacancyForRespondedDto> getVacanciesForRespondedApplicantsByUserId(Authentication authentication) {
        User currentUser = getUserFromAuth(authentication.getPrincipal().toString());
        List<Vacancy> vacancies = respondedApplicantDao.getVacanciesForRespondedApplicantsByUserId(currentUser.getId());


        if (vacancies.isEmpty()) {
            throw new VacancyNotFoundException("Can't find vacancies that you responded: ");
        }

        List<VacancyForRespondedDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyForRespondedDto.builder().name(e.getName()).description(e.getDescription()).categoryName(e.getCategory().getName()).salary(e.getSalary()).experienceFrom(e.getExperienceFrom()).experienceTo(e.getExperienceTo()).isActive(e.getIsActive()).createdDate(e.getCreatedDate()).updateTime(e.getUpdateTime()).build()));
        return dtos;
    }

    @Override
    @SneakyThrows
    public List<RespondedApplicantDtoForChat> getRespondedApplicantDtoForChatByUserId(Authentication authentication) {
        User currentUser = getUserFromAuth(authentication.getPrincipal().toString());
        List<RespondedApplicant> respondedApplicantsTest = respondedApplicantDao.getRespondedApplicantsByUserId(currentUser.getId());
        List<RespondedApplicant> respondedApplicants = new ArrayList<>();
        for (RespondedApplicant applicant : respondedApplicantsTest) {
            respondedApplicants.add(respondedApplicantRepository.findById(applicant.getId()).orElseThrow());
        }

        List<RespondedApplicantDtoForChat> dtos = new ArrayList<>();

        for (RespondedApplicant respondedApplicant : respondedApplicants) {
            Vacancy vacancy = vacancyRepository.findById(respondedApplicant.getVacancy().getId()).orElseThrow();
            Resume resume = resumeRepository.findById(respondedApplicant.getResume().getId()).orElseThrow();
            RespondedApplicantDtoForChat dto = RespondedApplicantDtoForChat.builder()
                    .id(respondedApplicant.getId())
                    .vacancy(vacancy)
                    .resume(resume)
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }


    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user"));
    }

    @Override
    @SneakyThrows
    public List<UserDto> getRespondedUsersForVacancies(Integer vacancyId, Authentication authentication) {
        User currentUser = getUserFromAuth(authentication.getPrincipal().toString());

        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow();
        if (!vacancy.getAuthor().getId().equals(currentUser.getId())) {
            throw new VacancyNotFoundException("Vacancy not found or access denied.");
        }

        List<User> users = respondedApplicantDao.getRespondedUsersForVacancies(vacancyId);
        for (int i = 0; i < users.size(); i++) {
        }
        return users.stream().map(this::convertToUserDto).collect(Collectors.toList());
    }

    private UserDto convertToUserDto(User user) {
        return UserDto.builder().name(user.getName()).surname(user.getSurname()).email(user.getEmail()).age(user.getAge()).phoneNumber(user.getPhoneNumber()).avatar(user.getAvatar()).accountType(AccountType.valueOf(user.getAccountType())).build();
    }

}
