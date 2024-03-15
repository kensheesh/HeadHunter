package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;

    public List<Resume> getResumes() {
        String sql = """
                select * from resumes;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getResumesByCategory(int categoryId) {
        String sql = """
                select * from resumes
                where categoryId = ?;
                """;

        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public List<Resume> getResumesByUserId(int userId) {
        String sql = """
                select * from resumes
                where userId = ?;
                """;

        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public Optional<Resume> getResumeById(int id) {
        String sql = """
                            
                    select * from resumes
                where id = ?;
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }
    public int createResume(Resume resume) {
        String sql = """
                insert into resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
                values(?,?,?,?,?,?,?);
                 """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, resume.getName());
            ps.setInt(2, resume.getUserId());
            ps.setInt(3, resume.getCategoryId());
            ps.setInt(4, resume.getSalary());
            ps.setBoolean(5, resume.isActive());
            ps.setTimestamp(6, Timestamp.valueOf(resume.getCreatedTime()));
            ps.setTimestamp(7, Timestamp.valueOf(resume.getUpdateTime()));

            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public void editResume(Resume resume) {
        String sql = """
            UPDATE resumes
            SET name = ?, userId = ?, categoryId = ?, salary = ?, isActive = ?, createdTime = ?, updateTime = ?
            WHERE id = ?;
            """;

        template.update(sql, resume.getName(), resume.getUserId(), resume.getCategoryId(), resume.getSalary(), resume.isActive(), Timestamp.valueOf(resume.getCreatedTime()), Timestamp.valueOf(resume.getUpdateTime()), resume.getId());
    }

    public void deleteResumeById(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        template.update(sql, id);
    }
}
