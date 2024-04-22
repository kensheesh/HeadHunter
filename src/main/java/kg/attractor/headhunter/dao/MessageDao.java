package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageDao {
    private final JdbcTemplate template;

    public List<Message> getAllMessagesByRespondedApplicant(Integer respondedApplicantId) {
        String sql = """
                select * from messages where RESPONDEDAPPLICANTSID = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Message.class), respondedApplicantId);
    }
}
