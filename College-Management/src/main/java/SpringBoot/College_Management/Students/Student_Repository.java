package SpringBoot.College_Management.Students;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface Student_Repository extends JpaRepository<Student_Entity,String> {
    boolean existsByEmail(String email);
    boolean existsByRollNo(String rollNo);
    boolean existsByName(String name);
    void deleteByStudentIdAndName(String id,String name);
    Optional<Student_Entity> findByStudentIdAndName(String id,String name);

   Optional<Student_Entity> findByEmail(String email);
    List<Student_Entity> findBy(Sort sort);

    // Using BETWEEN
    List<Student_Entity> findByDateOfAdmissionBetween(LocalDate start, LocalDate end);

    // Using greater than or equal and less than or equal
    List<Student_Entity> findByDateOfAdmissionGreaterThanEqualAndDateOfAdmissionLessThanEqual(LocalDate start, LocalDate end);

    Optional<Student_Entity> findByStudentId(String studentId);

    boolean existsBySecretCode(String secretCode);

    boolean existsByStudentId(String studentId);
}
