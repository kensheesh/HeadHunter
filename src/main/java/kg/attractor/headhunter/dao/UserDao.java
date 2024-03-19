package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.User;
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
public class UserDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getUsers() {
        String sql = """
                select * from users
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserById(int id) {
        String sql = """
                select * from users
                where id = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public List<User> getUserByName(String name) {
        String sql = """
                select * from users
                where name = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), name);
    }


    public List<User> getUserByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users
                where phoneNumber = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber);
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                )
        );
    }

    public boolean doesUserExistByEmail(String email) {
        String sql = """
                select exists(select 1 from users where email = ?);
                """;
        return template.queryForObject(sql, Boolean.class, email);
    }

    public void editUser(User user) {
        String sql = """
                UPDATE users
                SET name = ?, surname = ?, age = ?,  password = ?, avatar = ?
                WHERE id = ?;
                """;

        template.update(sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getPassword(),
                user.getAvatar(),
                user.getId());
    }

    public void deleteUserById(int id) {
        String sql = """
                delete from users
                where id = ?
                """;
        template.update(sql, id);
    }

    public void addAvatar(User user) {
        String sql = """
                UPDATE users
                SET avatar = ?
                WHERE id = ?;
                """;
        template.update(sql, user.getAvatar(), user.getId());
    }

    public void createUser(User user) {
        String sql = """
                insert into users(name, surname, email, password, phoneNumber, avatar, accountType)
                values (:name, :surname, :email, :password, :phoneNumber, :avatar, :accountType)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("avatar", user.getAvatar())
                .addValue("accountType", user.getAccountType().name())
        );
    }
}