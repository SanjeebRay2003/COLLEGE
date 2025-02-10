package SpringBoot.College_Management.Subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subject_Repository extends JpaRepository<Subject_Entity,Long> {
    boolean existsByName(String name);
}
