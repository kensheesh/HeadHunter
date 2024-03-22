package kg.attractor.headhunter.dao;

import kg.attractor.headhunter.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
        String sql = """
                insert into workExperienceInfo (resumeId ,years, companyName, position, responsibilities)
                values (:resumeId ,:years, :companyName, :position, :responsibilities)
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", workExperienceInfo.getResumeId())
                .addValue("years", workExperienceInfo.getYears())
                .addValue("companyName", workExperienceInfo.getCompanyName())
                .addValue("position", workExperienceInfo.getPosition())
                .addValue("responsibilities", workExperienceInfo.getResponsibilities()));
    }

    public void editWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
        String sql = """
                update workExperience
                set years = ?, companyName = ?, position = ?, responsibilities = ?
                where resumeId = ?;
                """;
        template.update(sql, workExperienceInfo.getYears(), workExperienceInfo.getCompanyName(), workExperienceInfo.getPosition(),
                workExperienceInfo.getResponsibilities(), workExperienceInfo.getResumeId());
    }
}
