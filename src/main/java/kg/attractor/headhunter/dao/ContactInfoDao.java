package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createContactInfo(ContactInfo contactInfo) {
        String sql = """
                insert into contactsInfo(resumeId,ContactTypeId, content)
                values(:resumeId, :ContactTypeId, :content)
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", contactInfo.getResumeId())
                .addValue("ContactTypeId", contactInfo.getContactTypeId())
                .addValue("content", contactInfo.getContent()));
    }
}
