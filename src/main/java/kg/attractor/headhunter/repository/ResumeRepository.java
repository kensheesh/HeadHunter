package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.Category;
import kg.attractor.headhunter.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findByAuthorId(Integer userId);

    List<Resume> findByCategory(Category category);

    Page<Resume> findByCategoryAndIsActive(Category categoryFromDB, boolean b, Pageable pageable);

    Page<Resume> findByIsActive(boolean b, Pageable pageable);
}
