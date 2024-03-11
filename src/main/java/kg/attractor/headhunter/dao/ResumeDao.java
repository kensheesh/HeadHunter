package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;

    public Optional<List<Resume>> getResumesByCategory(int categoryId) {
        String sql = """
                select * from resumes
                where categoryId = ?;
                """;

        return Optional.of(
                Collections.singletonList(DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId)
                ))
        );
    }
}
