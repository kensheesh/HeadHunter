package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.Category;
import kg.attractor.headhunter.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByCategoryId(Integer categoryId);

    List<Vacancy> findByAuthorId(Integer id);
}
