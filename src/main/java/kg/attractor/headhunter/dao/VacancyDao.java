package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Vacancy> getVacancies() {
        String sql = """
                select * from vacancies
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(int id) {
        String sql = """
                select * from vacancies
                where id = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    public List<Vacancy> getVacancyByName(String name) {
        String sql = """
                select * from vacancies
                where name = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), name);
    }

    public List<Vacancy> getVacanciesByCategoryId(int categoryId) {
        String sql = """
                select * from vacancies
                where categoryId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public List<Vacancy> getVacanciesByCategoryName(String categoryName) {
        String sql = """
                SELECT v.* FROM vacancies v
                JOIN categories c ON v.categoryId = c.id
                WHERE c.name = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryName);
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






    public void createVacancy(Vacancy vacancy) {
        String sql = """
                insert into vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId, createdDate, updateTime)
                values (:name, :description, :categoryId, :salary, :experienceFrom, :experienceTo, :isActive, :authorId, :createdDate, :updateTime)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("experienceFrom", vacancy.getExperienceFrom())
                .addValue("experienceTo", vacancy.getExperienceTo())
                .addValue("isActive", vacancy.isActive())
                .addValue("authorId", vacancy.getAuthorId())
                .addValue("createdDate", vacancy.getCreatedDate())
                .addValue("updateTime", vacancy.getUpdateTime())
        );
    }

    public void editVacancy(Vacancy vacancy) {
        String sql = """
                UPDATE vacancies
                SET name = ?, description = ?, salary = ?,
                    experienceFrom = ?, experienceTo = ?,
                    isActive = ?, updateTime = ?
                WHERE id = ?;
                """;

        template.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getSalary(),
                vacancy.getExperienceFrom(), vacancy.getExperienceTo(), vacancy.isActive(),
                vacancy.getUpdateTime(), vacancy.getId());
    }

    public void deleteVacancyById(int id) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        template.update(sql, id);
    }
}