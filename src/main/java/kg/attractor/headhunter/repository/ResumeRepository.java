package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.Category;
import kg.attractor.headhunter.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findAllByUserId(Integer userId);

    List<Resume> findByCategory(Category category);
}
