package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

        List<Resume> resumes = template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);

        return resumes.isEmpty() ? Optional.empty() : Optional.of(resumes);
    }

    public Optional<List<Resume>> getResumeByUserId(int userId) {
        String sql = """
                select * from resumes
                where userId = ?;
                """;

        List<Resume> resumes = template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);

        return resumes.isEmpty() ? Optional.empty() : Optional.of(resumes);
    }
}
