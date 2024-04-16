package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Message;
import kg.attractor.headhunter.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageDao {
    private final JdbcTemplate template;

    public List<Message> getAllMessagesByAuthorId(Integer authorId) {
        String sql = "SELECT * FROM messages WHERE author_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Message.class), authorId);
    }

}
