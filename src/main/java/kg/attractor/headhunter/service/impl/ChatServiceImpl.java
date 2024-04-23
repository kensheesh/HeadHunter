package kg.attractor.headhunter.service.impl;

import io.swagger.v3.oas.models.security.SecurityScheme;
import kg.attractor.headhunter.dao.MessageDao;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.ChatMessageDto;
import kg.attractor.headhunter.dto.MessageSendDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.model.Message;
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
    private final UserDao userDao;
    private final UserService userService;
    private final RespondedApplicantService respondedApplicantService;
    private final MessageDao messageDao;
    @Override
    public List<ChatMessageDto> getAllMessagesByRespondedApplicant(Integer respondedApplicantId) {
        List<Message> messages = messageDao.getAllMessagesByRespondedApplicant(respondedApplicantId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void sendMessage(MessageSendDto messageSendDto, Integer respondedApplicantId) {
        System.out.println(messageSendDto);
        Message message = new Message();
        message.setUserFromId(messageSendDto.getUserFromId());
        message.setUserToId(messageSendDto.getUserToId());
        message.setRespondedApplicantsId(messageSendDto.getRespondedApplicantsId());
        message.setContent(messageSendDto.getContent());
        message.setTimestamp(LocalDateTime.now());

        messageDao.save(message);
    }

    private ChatMessageDto convertToDto(Message message) {
        return ChatMessageDto.builder()
                .id(message.getId())
                .userFromId(userService.getUserById(message.getUserFromId()))
                .userToId(userService.getUserById(message.getUserToId()))
                .respondedApplicantId(message.getRespondedApplicantsId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .build();
    }
}
