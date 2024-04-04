package kg.attractor.headhunter.service;

import kg.attractor.headhunter.model.User;

import java.util.List;

public interface ProfileService {
    Object getUserProfile(Integer id);
    User getUserById(Integer id);
    List<?> getProfileContent(Integer id);
}
