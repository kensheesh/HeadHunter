package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createEducationInfo(EducationInfo educationInfo) {
        String sql = """
                insert into educationInfo(resumeId, institution, program, startDate, endDate, degree)
                values (:resumeId, :institution, :program, :startDate, :endDate, :degree)
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", educationInfo.getResumeId())
                .addValue("institution", educationInfo.getInstitution())
                .addValue("program", educationInfo.getProgram())
                .addValue("startDate", educationInfo.getStartDate())
                .addValue("endDate", educationInfo.getEndDate())
                .addValue("degree", educationInfo.getDegree()));
    }

    public List<EducationInfo> getEducationInfoByResumeId(int resumeId) {
        String sql = """
                select * from educationInfo
                where resumeId = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), resumeId);
    }

}