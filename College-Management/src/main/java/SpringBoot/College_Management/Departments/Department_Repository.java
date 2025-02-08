package SpringBoot.College_Management.Departments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Department_Repository  extends JpaRepository<Department_Entity,Long> {
}
