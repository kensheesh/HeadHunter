package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Resume> getResumes() {
        String sql = """
                select * from resumes;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getResumesByCategoryId(int categoryId) {
        String sql = """
                select * from resumes
                where categoryId = ?;
                """;

        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public List<Resume> getResumesByCategoryName(String categoryName) {
        String sql = """
                select r.* from resumes r
                join categories c on r.categoryId = c.id
                where c.name = ?;
                """;

        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryName);
    }


    public List<Resume> getActiveResumes() {
        String sql = """
                select * from resumes where isActive = true
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getActiveResumesByUserId(int userId) {
        String sql = """
                select * from resumes
                where UserId = ? and isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
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

    public void createResume(Resume resume) {
        String sql = """
                insert into resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
                values (:name, :userId, :categoryId, :salary, :isActive, :createdTime, :updateTime)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("userId", resume.getUserId())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", resume.isActive())
                .addValue("createdTime", resume.getCreatedTime())
                .addValue("updateTime", resume.getUpdateTime())
        );
    }


    public void editResume(Resume resume) {
        String sql = """
                UPDATE resumes
                SET name = ?, salary = ?, isActive = ?, updateTime = ?
                WHERE id = ?;
                """;

        template.update(sql, resume.getName(), resume.getSalary(), resume.isActive(), Timestamp.valueOf(resume.getUpdateTime()), resume.getId());
    }

    public void deleteResumeById(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        template.update(sql, id);
    }
}
