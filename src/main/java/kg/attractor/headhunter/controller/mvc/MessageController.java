package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.model.Message;
import kg.attractor.headhunter.service.ChatService;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import kg.attractor.headhunter.service.impl.RespondedApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/chat/{respondedApplicantId}")
    public String getChatMessages(@PathVariable("respondedApplicantId") Integer respondedApplicantId, Model model) {
        List<ChatMessageDto> chatMessages = chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("respondedApplicantId", chatMessages.get(0).getRespondedApplicantId());
        return "chat/chat";
    }
}
