package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Department_Repository  extends JpaRepository<Department_Entity,Long> {
    boolean existsByName(String name);
    boolean existsByCourseId(Long courseId);
}
