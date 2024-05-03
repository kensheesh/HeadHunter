package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.ContactInfo;
import kg.attractor.headhunter.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
    List<ContactInfo> findByResumeId(Integer resumeId);

}
