package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByCategoryId(Integer categoryId);

    List<Vacancy> findByAuthorId(Integer id);

    Page<Vacancy> findByCategoryIdAndIsActive(Integer id, boolean b, Pageable pageable);

    Page<Vacancy> findByIsActive(boolean b, Pageable pageable);

    Page<Vacancy> findByAuthorIdAndIsActive(Integer id, boolean b, Pageable pageable);
}
