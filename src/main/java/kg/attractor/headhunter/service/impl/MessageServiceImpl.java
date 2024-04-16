package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.MessageDao;
import kg.attractor.headhunter.dao.UserDao;
import kg.attractor.headhunter.dto.MessageForChatDto;
import kg.attractor.headhunter.dto.MyMessagesAndOtherInfoDto;
import kg.attractor.headhunter.dto.UserDto;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.Message;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final UserDao userDao;
    private final MessageDao messageDao;

    @Override
    @SneakyThrows
    public MyMessagesAndOtherInfoDto getMyMessages(Integer vacancyId, Integer resumeId, Authentication authentication) {
        User user = getUserFromAuth(authentication.getPrincipal().toString());
        MyMessagesAndOtherInfoDto myMessagesAndOtherInfoDto = new MyMessagesAndOtherInfoDto();

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPassword(user.getPassword());
        userDto.setAccountType(user.getAccountType());

        List<MessageForChatDto> messageForChatDtoList = new ArrayList<>();

        if (user.getAccountType().equals(AccountType.APPLICANT)) {
            List<Message> messagesOfUser = messageDao.getAllMessagesByAuthorId(user.getId());
        }


        for (Message message : messagesOfUser) {
            MessageForChatDto messageDto = new MessageForChatDto();
            messageDto.setId(message.getId());
            messageDto.setRespondedApplicantId(message.getRespondedApplicantId());
            messageDto.setContent(message.getContent());
            messageDto.setTimestamp(message.getTimestamp());
            messageForChatDtoList.add(messageDto);
        }


        myMessagesAndOtherInfoDto.setAuthor(userDto);
        myMessagesAndOtherInfoDto.setMessages(messageForChatDtoList);
        return myMessagesAndOtherInfoDto;
    }

    @SneakyThrows
    public User getUserFromAuth(String auth) {
        int x = auth.indexOf("=");
        int y = auth.indexOf(",");
        String email = auth.substring(x + 1, y);
        return userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("can't find user with this email"));
    }
}
