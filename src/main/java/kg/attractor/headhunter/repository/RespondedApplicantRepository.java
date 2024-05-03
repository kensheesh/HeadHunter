package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Integer> {
}
