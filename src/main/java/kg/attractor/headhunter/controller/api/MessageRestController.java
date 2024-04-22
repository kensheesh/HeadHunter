package kg.attractor.headhunter.controller.api;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class MessageRestController {
    private final ChatService chatService;

    @GetMapping("/{respondedApplicantId}/messages")
    public List<ChatMessageDto> getChatMessages(@PathVariable("respondedApplicantId") Integer respondedApplicantId) {
        List<ChatMessageDto> chatMessageDto = chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
        return chatMessageDto;
    }
}
