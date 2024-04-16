package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.RespondedApplicant;
import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void create(RespondedApplicant respondedApplicant) {
        String sql = "INSERT INTO respondedApplicants (resumeId, vacancyId, confirmation) VALUES (?, ?, ?)";
        template.update(sql, respondedApplicant.getResumeId(), respondedApplicant.getVacancyId(), respondedApplicant.isConfirmation());
    }

    public RespondedApplicant getRespondedApplicantByResumeIdAndVacancyId(Integer vacancyId, Integer resumeId) {
        String sql = """
                select * from respondedApplicants
                where resumeId = ?
                and vacancyId = ?;
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), resumeId, vacancyId);
    }
}
