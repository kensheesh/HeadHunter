package kg.attractor.headhunter.repository;

import kg.attractor.headhunter.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactType, Integer> {
}
