package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Category> getCategoryByName(String name) {
        String sql = """
                select * from categories
                where name like ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Category.class), name)
                )
        );
    }

    public Category getCategoryById(int id) {
        String sql = """
                select * from categories
                where id like ?
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), id);
    }
}
