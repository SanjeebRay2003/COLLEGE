package SpringBoot.College_Management.Subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Subject_Repository extends JpaRepository<Subject_Entity,String> {
    boolean existsBySubject(String name);
    void deleteBySubject(String subjectName);
    Optional<Subject_Entity> findBySubject(String subject);

}
