package SpringBoot.College_Management.Courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Course_Repository extends JpaRepository<Course_Entity, Long> {
    boolean existsByName(String name);
//    boolean existsByStudentId(Long Id);
}
