package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Category getCategoryById(String name) {
        String sql = """
                select * from categories
                where name like ?
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), name);
    }

}
