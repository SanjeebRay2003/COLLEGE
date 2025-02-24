package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Professors.Professor_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Student_Repository extends JpaRepository<Student_Entity,Long> {
    boolean existsByEmail(String email);
    boolean existsByRollNo(String rollNo);
    boolean existsByName(String name);
    void deleteByStudentIdAndName(Long id,String name);
    Optional<Student_Entity> findByStudentIdAndName(Long id,String name);
}
