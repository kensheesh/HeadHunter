package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.User;
import kg.attractor.headhunter.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final JdbcTemplate template;

    public Optional<List<Vacancy>> getVacanciesForRespondedApplicantsByUserId(int userId) {
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
               WHERE r.userId = ? AND ra.confirmation = TRUE;
                """;
        List<Vacancy> vacancies = template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
        return vacancies.isEmpty() ? Optional.empty() : Optional.of(vacancies);
    }

    public Optional<List<User>> getRespondedUsersForVacancies(int vacancyId) {
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

        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
        return users.isEmpty() ? Optional.empty() : Optional.of(users);
    }
}
