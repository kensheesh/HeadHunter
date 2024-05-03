package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.RespondedApplicantRepository;
import kg.attractor.headhunter.repository.ResumeRepository;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.repository.VacancyRepository;
import kg.attractor.headhunter.service.ChatService;
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
    private final ChatService chatService;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final UserRepository userRepository;

    @GetMapping("/chat/{respondedApplicantId}")
    public String getChatMessages(@PathVariable("respondedApplicantId") Integer respondedApplicantId, Model model, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        List<ChatMessageDto> chatMessages = chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
        RespondedApplicant respondedApplicant = respondedApplicantRepository.findById(respondedApplicantId).orElseThrow();
        Integer userFromId = user.getId();
        Integer userToId;
        // проверяю кем является нынешний пользователь, дальше уже передаю userFromId и userToId,
        // это я сделал по причине того что у меня возникали проблемы при сохранении сообщений, были неверны id пользователей
        if (userFromId == vacancyRepository.findById(respondedApplicant.getVacancy().getId()).orElseThrow().getAuthor().getId()) {
            userToId = resumeRepository.findById(respondedApplicant.getResume().getId()).orElseThrow().getAuthor().getId();
        } else {
            userToId = vacancyRepository.findById(respondedApplicant.getVacancy().getId()).orElseThrow().getAuthor().getId();
        }

        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("respondedApplicantId", respondedApplicant.getId());
        model.addAttribute("userFromId", user.getId());
        model.addAttribute("userToId", userToId);
        model.addAttribute("guestUser", userRepository.findById(userToId).orElseThrow());
        return "chat/chat";
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
