package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Students.Student_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Course_Repository extends JpaRepository<Course_Entity, Long> {
    boolean existsByCourse(String name);
    Optional<Course_Entity> findByCourse(String course);
    void deleteByCourse(String course);
    Boolean existsByStudents(Student_Entity student);

//    void existsBySemester(String semester);

//    Optional<Course_Entity> findBySemesters(String semester);
}
