package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Resume;
import kg.attractor.headhunter.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate template;

    public List<Vacancy> getVacancies() {
        String sql = """
                select * from vacancies
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<List<Vacancy>> getVacanciesByCategory(int categoryId) {
        String sql = """
                select * from vacancies
                where categoryId = ?;
                """;

        List<Vacancy> vacancies = template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
        return Optional.ofNullable(vacancies.isEmpty() ? null : vacancies);
    }

    public Optional<List<Vacancy>> getVacanciesByUserId(int userId) {
        String sql = """
                select * from vacancies
                where authorId = ?;
                """;

        List<Vacancy> vacancies = template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);

        return vacancies.isEmpty() ? Optional.empty() : Optional.of(vacancies);
    }

    public List<Vacancy> getActiveVacancies() {
        String sql = """
            select * from vacancies where isActive = true
            """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

}

