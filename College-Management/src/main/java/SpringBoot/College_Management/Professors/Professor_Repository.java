package SpringBoot.College_Management.Professors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Professor_Repository extends JpaRepository<Professor_Entity,Long> {
    boolean existsByEmail(String email);
    boolean existsByContactNo(String contactNo);
}
