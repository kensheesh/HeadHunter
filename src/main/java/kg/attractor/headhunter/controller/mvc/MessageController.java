package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final ChatService chatService;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final UserRepository userRepository;

    @SneakyThrows
    @GetMapping("/chat/{respondedApplicantId}")
    public String getChatMessages(@PathVariable("respondedApplicantId") Integer respondedApplicantId, Model model, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = AccountType.valueOf(getUserFromAuth(auth.getPrincipal().toString()).getAccountType());
            model.addAttribute("type", accountType);
        }

        User user = getUserFromAuth(authentication.getPrincipal().toString());

        List<ChatMessageDto> chatMessages = chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
        RespondedApplicant respondedApplicant = respondedApplicantRepository.findById(respondedApplicantId).orElseThrow();
        Integer userFromId = user.getId();
        Integer userToId;
        if (Objects.equals(userFromId, vacancyRepository.findById(respondedApplicant.getVacancy().getId()).orElseThrow().getAuthor().getId())) {
            userToId = resumeRepository.findById(respondedApplicant.getResume().getId()).orElseThrow().getAuthor().getId();
        } else if (Objects.equals(userFromId, resumeRepository.findById(respondedApplicant.getVacancy().getId()).orElseThrow().getAuthor().getId())) {
            userToId = vacancyRepository.findById(respondedApplicant.getVacancy().getId()).orElseThrow().getAuthor().getId();
        } else {
            throw new UserNotFoundException("Chat not found");
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
