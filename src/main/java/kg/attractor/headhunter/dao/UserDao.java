package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.AccountType;
import kg.attractor.headhunter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getAllUsers() {
        String sql = """
                select * from users
                """;
        return template.query(sql, USER_ROW_MAPPER);
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users
                where phoneNumber like ?;
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, USER_ROW_MAPPER, phoneNumber)
                )
        );
    }

    public Integer getRoleIdByAccountType(AccountType accountType) {
        if (accountType.equals(AccountType.EMPLOYER)) {
            return 1;
        } else {
            return 2;
        }
    }

    public List<User> getAllEmployers() {
        String sql = """
                select * from users
                where accountType like 'EMPLOYER';
                """;
        return template.query(sql, USER_ROW_MAPPER);
    }

    public List<User> getAllApplicants() {
        String sql = """
                select * from users
                where accountType like 'APPLICANT';
                """;
        return template.query(sql, USER_ROW_MAPPER);
    }

    public Optional<User> getUserById(Integer id) {
        String sql = """
                select * from users
                where id = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, USER_ROW_MAPPER, id)
                )
        );
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email like ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, USER_ROW_MAPPER, email)
                )
        );
    }

    public boolean isExists(String email) {
        String sql = """
                select case when exists(select * from users
                where email like ?)
                then true else false end;
                """;
        return Boolean.TRUE.equals(template.queryForObject(sql, Boolean.class, email));
    }

    public List<User> getEmployersByName(String name) {
        String sql = """
                select * from users
                where name like ?
                and accountType like 'EMPLOYER';
                """;
        return template.query(sql, USER_ROW_MAPPER, name);
    }

    public List<User> getApplicantsByName(String name) {
        String sql = """
                select * from users
                where name like ?
                and accountType like 'APPLICANT';
                """;
        return template.query(sql, USER_ROW_MAPPER, name);
    }

    public List<User> getApplicantsBySurname(String surname) {
        String sql = """
                select * from users
                where surname like ?
                and accountType = 'APPLICANT'
                """;
        return template.query(sql, USER_ROW_MAPPER, surname);
    }

    public Optional<User> getApplicantByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users
                where phoneNumber like ?
                and accountType like 'APPLICANT';
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, USER_ROW_MAPPER, phoneNumber)
                )
        );
    }

    public Optional<User> getApplicantByEmail(String email) {
        String sql = """
                select * from users
                where email like ?
                and accountType like 'APPLICANT';
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, USER_ROW_MAPPER, email)
                )
        );
    }


    public void createUser(User user) {
        String sql = """
                insert into users(name, surname, age, email, password, phoneNumber, avatar, accountType, enabled, role_id)
                values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :accountType, :enabled, :role_id)
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("avatar", "base_avatar.jpg")
                .addValue("accountType", user.getAccountType().name())
                .addValue("enabled", true)
                .addValue("role_id", user.getRoleId())
        );
    }

    public void editUser(User user) {
        String sql = """
                UPDATE users
                SET name = ?, surname = ?, age = ?,  password = ?
                WHERE email like ?;
                """;

        template.update(sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getPassword(),
                user.getEmail());
    }


    public void addAvatar(User user) {
        String sql = """
                UPDATE users
                SET avatar = ?
                WHERE email like ?;
                """;
        template.update(sql, user.getAvatar(), user.getEmail());
    }
}