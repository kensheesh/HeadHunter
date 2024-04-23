package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.dto.MessageSendDto;

import java.util.List;

public interface ChatService {
    List<ChatMessageDto> getAllMessagesByRespondedApplicant(Integer respondedApplicantId);
    void sendMessage(MessageSendDto messageSendDto, Integer respondedApplicantId);
}
