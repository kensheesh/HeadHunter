package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.ChatMessageDto;

import java.util.List;

public interface ChatService {
    List<ChatMessageDto> getAllMessagesByRespondedApplicant(Integer respondedApplicantId);
}
