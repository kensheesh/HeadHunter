package kg.attractor.headhunter.controller.api;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.dto.MessageSendDto;
import kg.attractor.headhunter.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{respondedApplicantId}/send")
    public ResponseEntity<?> sendMessage(@PathVariable Integer respondedApplicantId, @RequestBody MessageSendDto messageSendDto) {
        try {
            chatService.sendMessage(messageSendDto, respondedApplicantId);
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending message: " + e.getMessage());
        }
    }
}
