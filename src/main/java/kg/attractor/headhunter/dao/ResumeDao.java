package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Resume> getAllActiveResumes() {
        String sql = """
                select * from resumes
                where isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getAllResumesByName(String name) {
        String sql = """
                select * from resumes
                where name like ?
                and isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), name);
    }

    public List<Resume> getAllResumesByCategoryId(Integer categoryId) {
        String sql = """
                select * from resumes
                where categoryId = ?
                and isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public List<Resume> getAllResumesOfApplicant(Integer id) {
        String sql = """
                select * from resumes
                where userId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------

    public Integer createResumeAndReturnId(Resume resume) {
        String sql = """
                insert into resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
                values (?,?,?,?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, resume.getName());
            ps.setInt(2, resume.getUserId());
            ps.setInt(3, resume.getCategoryId());
            ps.setBigDecimal(4, resume.getSalary());
            ps.setBoolean(5, resume.getIsActive());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }


    public void editResume(Resume resume) {
        String sql = """
                UPDATE resumes
                SET name = ?, salary = ?, isActive = ?, updateTime = ?
                WHERE id = ?;
                """;

        template.update(sql, resume.getName(), resume.getSalary(), resume.getIsActive(), Timestamp.valueOf(LocalDateTime.now()), resume.getId());
    }

    public boolean isApplicantHasResumeById(User user, Integer resumeId) {
        String sql = """
                SELECT COUNT(*) FROM resumes
                WHERE id = ? AND userId = ?
                """;

        Integer count = template.queryForObject(sql, Integer.class, resumeId, user.getId());
        return count != null && count > 0;
    }


    public List<Resume> getResumesByUserIdAndName(int userId, String name) {
        String sql = """
                select * from resumes
                where userId = ? and name = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId, name);
    }

    public List<Resume> getResumesByUserIdAndCategoryName(int userId, int categoryId) {

        String sql = """
                select * from resumes
                where userId = ? and categoryId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId, categoryId);
    }

    public List<Resume> getActiveResumesByUserId(int userId) {
        String sql = """
                select * from resumes
                where UserId = ? and isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    //
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

    public void deleteResumeById(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        template.update(sql, id);
    }
}
