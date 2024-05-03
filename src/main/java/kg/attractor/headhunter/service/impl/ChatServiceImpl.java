package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.dto.MessageSendDto;
import kg.attractor.headhunter.model.Message;
import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.repository.MessageRepository;
import kg.attractor.headhunter.repository.RespondedApplicantRepository;
import kg.attractor.headhunter.repository.UserRepository;
import kg.attractor.headhunter.service.ChatService;
import kg.attractor.headhunter.service.RespondedApplicantService;
import kg.attractor.headhunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserService userService;
    private final RespondedApplicantService respondedApplicantService;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RespondedApplicantRepository respondedApplicantRepository;

    @Override
    public List<ChatMessageDto> getAllMessagesByRespondedApplicant(Integer respondedApplicantId) {
        List<Message> messages = messageRepository.findByRespondedApplicantId(respondedApplicantId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void sendMessage(MessageSendDto messageSendDto, Integer respondedApplicantId) {
        System.out.println(messageSendDto);
        Message message = new Message();
        User userFrom = userRepository.findById(messageSendDto.getUserFromId()).orElseThrow();
        User userTo = userRepository.findById(messageSendDto.getUserToId()).orElseThrow();
        RespondedApplicant respondedApplicant = respondedApplicantRepository.findById(respondedApplicantId).orElseThrow();

        message.setUserFrom(userFrom);
        message.setUserTo(userTo);
        message.setRespondedApplicant(respondedApplicant);
        message.setContent(messageSendDto.getContent());
        message.setTimestamp(LocalDateTime.now());

        messageRepository.save(message);
    }

    private ChatMessageDto convertToDto(Message message) {
        return ChatMessageDto.builder()
                .id(message.getId())
                .userFromId(userService.getUserById(message.getUserFrom().getId()))
                .userToId(userService.getUserById(message.getUserTo().getId()))
                .respondedApplicantId(message.getRespondedApplicant().getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .build();
    }
}
