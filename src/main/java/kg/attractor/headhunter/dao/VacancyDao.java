//package kg.attractor.headhunter.dao;
//
//import kg.attractor.headhunter.model.User;
//import kg.attractor.headhunter.model.Vacancy;
//import lombok.RequiredArgsConstructor;
//import org.springframework.dao.support.DataAccessUtils;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Component;
//
//import java.sql.PreparedStatement;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.DoubleSummaryStatistics;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class VacancyDao {
//    private final JdbcTemplate template;
//
//
//    public void updateVacancyUpdateTime(Integer vacancyId, LocalDateTime updateTime) {
//        String sql = "UPDATE vacancies SET updateTime = ? WHERE id = ?";
//        template.update(sql, updateTime, vacancyId);
//    }
//
//    public List<Vacancy> getAllActiveVacancies() {
//        String sql = """
//                select * from vacancies
//                where isActive = true;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
//    }
//
//    public List<Vacancy> getAllActiveVacanciesByName(String name) {
//        String sql = """
//                select * from vacancies
//                where name like ?
//                and isActive = true;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), name);
//    }
//
//    public List<Vacancy> getAllActiveVacanciesByCategoryId(Integer categoryId) {
//        String sql = """
//                select * from vacancies
//                where categoryId = ?
//                and isActive = true;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
//    }
//
//    public List<Vacancy> getAllVacanciesOfEmployer(Integer authorId) {
//        String sql = """
//                select * from vacancies
//                where authorId = ? order by updateTime desc;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), authorId);
//    }
//
//
//    public List<Vacancy> getAllActiveVacanciesBySalaryDescending() {
//        String sql = """
//                select * from vacancies
//                where isActive = true
//                order by salary desc;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
//    }
//
//    public List<Vacancy> getAllActiveVacanciesBySalaryAscending() {
//        String sql = """
//                select * from vacancies
//                where isActive = true
//                order by salary asc;
//                """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
//    }
//
//    public List<Vacancy> getAllActiveVacanciesByUpdateTimeDescending() {
//        String sql = """
//                select * from vacancies
//                where isActive = true
//                order by updateTime desc;
//                 """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
//    }
//
//    public List<Vacancy> getAllActiveVacanciesByUpdateTimeAscending() {
//        String sql = """
//                select * from vacancies
//                where isActive = true
//                order by updateTime asc
//                ;
//                  """;
//        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
//    }
//
//    public Integer createVacancy(Vacancy vacancy) {
//        String sql = """
//                insert into vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId, createdDate, updateTime)
//                values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
//                """;
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        template.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
//            ps.setString(1, vacancy.getName());
//            ps.setString(2, vacancy.getDescription());
//            ps.setInt(3, vacancy.getCategoryId());
//            ps.setBigDecimal(4, vacancy.getSalary());
//            ps.setInt(5, vacancy.getExperienceFrom());
//            ps.setInt(6, vacancy.getExperienceTo());
//            ps.setBoolean(7, vacancy.getIsActive());
//            ps.setInt(8, vacancy.getAuthorId());
//            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
//            ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
//            return ps;
//        }, keyHolder);
//
//        return Objects.requireNonNull(keyHolder.getKey()).intValue();
//    }
//
//
//    public void editVacancy(Vacancy vacancy) {
//        String sql = """
//                update vacancies
//                set name = ?, description = ?, categoryId = ?, salary = ?,
//                    experienceFrom = ?, experienceTo = ?,
//                    isActive = ?, updateTime = ?
//                where id = ?;
//                """;
//
//        template.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(), vacancy.getSalary(),
//                vacancy.getExperienceFrom(), vacancy.getExperienceTo(), vacancy.getIsActive(),
//                LocalDateTime.now(), vacancy.getId());
//    }
//
//    public void deleteVacancyById(Integer id) {
//        String sql = """
//                delete from vacancies
//                where id = ?;
//                """;
//        template.update(sql, id);
//    }
//
//    public Optional<Vacancy> getVacancyById(Integer id) {
//        String sql = """
//                select * from vacancies
//                where id = ?
//                """;
//        return Optional.ofNullable(
//                DataAccessUtils.singleResult(
//                        template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
//                )
//        );
//    }
//
//    public boolean isEmployerHasVacancyById(User user, Integer vacancyId) {
//        String sql = """
//                SELECT COUNT(*) FROM vacancies
//                WHERE id = ? AND authorId = ?
//                """;
//
//        Integer count = template.queryForObject(sql, Integer.class, vacancyId, user.getId());
//        return count != null && count > 0;
//    }
//}