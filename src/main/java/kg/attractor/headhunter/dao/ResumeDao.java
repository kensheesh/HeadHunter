package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    public Integer createResumeAndReturnId(Resume resume) {
        String sql = """
                insert into resumes (title, authorId, categoryId, salary, isActive, createdTime, updateTime)
                values (?,?,?,?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, resume.getTitle());
            ps.setInt(2, resume.getAuthorId());
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
                SET title = ?, salary = ?, isActive = ?, updateTime = ?
                WHERE id = ?;
                """;

        template.update(sql, resume.getTitle(), resume.getSalary(), resume.getIsActive(), Timestamp.valueOf(LocalDateTime.now()), resume.getId());
    }

//
//    public List<Resume> getResumes() {
//        String sql = """
//                select * from resumes;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
//    }
//
//    public List<Resume> getResumesByCategoryId(int categoryId) {
//        String sql = """
//                select * from resumes
//                where categoryId = ?;
//                """;
//
//        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
//    }
//
//    public List<Resume> getResumesByCategoryName(String categoryName) {
//        String sql = """
//                select r.* from resumes r
//                join categories c on r.categoryId = c.id
//                where c.name = ?;
//                """;
//
//        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryName);
//    }
//
//    public List<Resume> getResumesByTitle(String title) {
//        String sql = """
//            select * from resumes
//            where name = ?;
//            """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), title);
//    }
//
//
//
//    public List<Resume> getActiveResumes() {
//        String sql = """
//                select * from resumes where isActive = true
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
//    }
//
//    public List<Resume> getActiveResumesByUserId(int userId) {
//        String sql = """
//                select * from resumes
//                where UserId = ? and isActive = true;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
//    }
//
    public List<Resume> getResumesByUserId(int userId) {
        String sql = """
                select * from resumes
                where userId = ?;
                """;

        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }
//
//    public Optional<Resume> getResumeById(int id) {
//        String sql = """
//
//                    select * from resumes
//                where id = ?;
//                """;
//
//        return Optional.ofNullable(
//                DataAccessUtils.singleResult(
//                        template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
//                )
//        );
//    }
//
//
//
//
//    public void deleteResumeById(int id) {
//        String sql = "DELETE FROM resumes WHERE id = ?";
//        template.update(sql, id);
//    }
}
