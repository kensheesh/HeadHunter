package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationInfoRepository extends JpaRepository<EducationInfo, Integer> {
    List<EducationInfo> findByResumeId(Integer resumeId);

}
