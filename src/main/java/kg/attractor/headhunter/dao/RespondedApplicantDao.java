package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final JdbcTemplate template;

    public List<Vacancy> getVacanciesForRespondedApplicantsByUserId(int userId) {
        String sql = """
                 SELECT
                    v.id,
                    v.name,
                    v.description,
                    v.categoryId,
                    v.salary,
                    v.experienceFrom,
                    v.experienceTo,
                    v.authorId,
                    v.createdDate,
                    v.updateTime,
                    v.isActive AS active
                FROM respondedApplicants ra
                JOIN resumes r ON ra.resumeId = r.id
                JOIN vacancies v ON ra.vacancyId = v.id
                WHERE r.userId = ?;
                 """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
    }

    public List<User> getRespondedUsersForVacancies(Integer vacancyId) {
        String sql = """
                            
                    SELECT
                                    u.id,
                                    u.name,
                                    u.surname,
                                    u.age,
                                    u.email,
                                    u.password,
                                    u.phoneNumber,
                                    u.avatar,
                                    u.accountType
                                FROM respondedApplicants ra
                                JOIN resumes r ON ra.resumeId = r.id
                                JOIN users u ON r.userId = u.id
                                WHERE ra.vacancyId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }

    public Integer create(RespondedApplicant respondedApplicant) {
        String sql = "INSERT INTO respondedApplicants (resumeId, vacancyId, confirmation) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, respondedApplicant.getResumeId());
            ps.setInt(2, respondedApplicant.getVacancyId());
            ps.setBoolean(3, respondedApplicant.isConfirmation());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public RespondedApplicant getRespondedApplicantByResumeIdAndVacancyId(Integer vacancyId, Integer resumeId) {
        String sql = """
                select * from respondedApplicants
                where resumeId = ?
                and vacancyId = ?;
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), resumeId, vacancyId);
    }


    public List<RespondedApplicant> getRespondedApplicantsByUserId(int userId) {
        String sql = "SELECT * FROM respondedApplicants WHERE resumeId IN (SELECT id FROM resumes WHERE userId = ?) OR vacancyId IN (SELECT id FROM vacancies WHERE authorId = ?)";
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), userId, userId);
    }

    public RespondedApplicant getRespondedApplicantById(int id) {
        String sql = "SELECT * FROM respondedApplicants WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), id);
    }
}
