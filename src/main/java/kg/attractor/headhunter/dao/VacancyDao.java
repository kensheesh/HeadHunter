package kg.attractor.headhunter.dao;

import jakarta.servlet.http.PushBuilder;
import kg.attractor.headhunter.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Vacancy> getVacancyByName(String name) {
        String sql = """
                select * from vacancies
                where name = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), name);
    }

    public List<Vacancy> getVacanciesByCategory(int categoryId) {
        String sql = """
                select * from vacancies
                where categoryId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);

    }

    public List<Vacancy> getVacanciesByUserId(int userId) {
        String sql = """
                select * from vacancies
                where authorId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
    }

    public List<Vacancy> getActiveVacanciesByUserId(int userId) {
        String sql = """
                select * from vacancies
                where authorId = ? and isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
    }

    public List<Vacancy> getActiveVacancies() {
        String sql = """
                select * from vacancies where isActive = true
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesBySalaryDescending() {
        String sql = """
                 select * from vacancies order by salary desc;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesBySalaryAscending() {
        String sql = """
                 select * from vacancies order by salary asc;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByUpdateTimeDescending() {
        String sql = """
                select * from vacancies order by updateTime desc;
                 """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByUpdateTimeAscending() {
        String sql = """
                select * from vacancies order by updateTime asc;
                 """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
}

