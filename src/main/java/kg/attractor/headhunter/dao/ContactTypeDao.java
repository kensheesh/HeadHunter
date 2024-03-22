package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactTypeDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ContactType getContactTypeIdByName(String name) {
        String sql = """
                select * from contactTypes
                where type = ?;
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(ContactType.class), name);
    }
}
