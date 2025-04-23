package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Department_Repository  extends JpaRepository<Department_Entity,String> {
    boolean existsByDepartment(String department);
    boolean existsByCourse(Course_Entity courseName);
    void deleteByDepartment(String department);
    Optional<Department_Entity> findByDepartment(String department);
}
