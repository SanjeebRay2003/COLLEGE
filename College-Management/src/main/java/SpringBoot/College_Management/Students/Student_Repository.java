package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Courses.Course_Entity;
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

    // Using greater than or equal and less than or equal
    List<Student_Entity> findByDateOfAdmissionGreaterThanEqualAndDateOfAdmissionLessThanEqual(LocalDate start, LocalDate end);

    Optional<Student_Entity> findByStudentId(String studentId);

    boolean existsBySecretCode(String secretCode);

    boolean existsByStudentId(String studentId);

    Optional<Student_Entity> findByRollNo(String rollNo);


    // Sorting

    List<Student_Entity> findBy(Sort sort);

    List<Student_Entity> findBySemester(String semester);

//    List<Student_Entity> findByCourse(String course); // no need to do here specially we can direct use in course controller
    // search with course name ane it gives all the student related to that course

    List<Student_Entity> findByName(String name);

    List<Student_Entity> findByIsActive(Boolean active);

    List<Student_Entity> findByDateOfAdmissionBetween(LocalDate startDate, LocalDate endDate);

    List<Student_Entity> findByRollNoBetween(String start, String end);

}
