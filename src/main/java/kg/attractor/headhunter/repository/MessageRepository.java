package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRespondedApplicantId(Integer respondedApplicantId);
}
