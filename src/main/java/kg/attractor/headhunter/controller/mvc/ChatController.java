package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.service.MessageService;
import kg.attractor.headhunter.service.ResumeService;
import kg.attractor.headhunter.service.UserService;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final MessageService messageService;

    @GetMapping("chat/{vacancyId}/{resumeId}")
    public String viewChatOfEmployerAndApplicant(Model model, Authentication authentication, @PathVariable Integer vacancyId, @PathVariable Integer resumeId) {
        model.addAttribute("user1", messageService.()vacancyId, resumeId, authentication);
        return "chat/chat";
    }
}
