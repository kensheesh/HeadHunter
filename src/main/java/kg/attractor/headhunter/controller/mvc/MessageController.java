package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.dao.RespondedApplicantDao;
import kg.attractor.headhunter.dao.ResumeDao;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dao.VacancyDao;
import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.ChatService;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final UserService userService;
    private final RespondedApplicantServiceImpl respondedApplicantService;
    private final ChatService chatService;
    private final UserDao userDao;
    private final VacancyDao vacancyDao;
    private final ResumeDao resumeDao;
    private final RespondedApplicantDao respondedApplicantDao;

    @GetMapping("/chat/{respondedApplicantId}")
    public String getChatMessages(@PathVariable("respondedApplicantId") Integer respondedApplicantId, Model model, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        List<ChatMessageDto> chatMessages = chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
        RespondedApplicant respondedApplicant = respondedApplicantDao.getRespondedApplicantById(respondedApplicantId);
        Integer userFromId = user.getId();
        Integer userToId;
        // проверяю кем является нынешний пользователь, дальше уже передаю userFromId и userToId,
        // это я сделал по причине того что у меня возникали проблемы при сохранении сообщений, были неверны id пользователей
        if (userFromId == vacancyDao.getVacancyById(respondedApplicant.getVacancyId()).orElseThrow().getAuthorId()) {
            userToId = resumeDao.getResumeById(respondedApplicant.getResumeId()).orElseThrow().getUserId();
        } else {
            userToId = vacancyDao.getVacancyById(respondedApplicant.getVacancyId()).orElseThrow().getAuthorId();
        }

        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("respondedApplicantId", respondedApplicant.getId());
        model.addAttribute("userFromId", user.getId());
        model.addAttribute("userToId", userToId);
        return "chat/chat";
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
