package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Integer> {
    List<WorkExperienceInfo> findByResumeId(Integer resumeId);

}
