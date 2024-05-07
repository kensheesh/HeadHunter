package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProfileService {
    UserDto getUserDto(Authentication authentication);

    List<?> getProfileContent(Authentication authentication);

}
