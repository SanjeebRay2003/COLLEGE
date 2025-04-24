package SpringBoot.College_Management.Security_Section.USER;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface User_Repository extends JpaRepository<User_Entity,String> {

    Optional<User_Entity> findByEmail(String username);

    boolean existsByEmail(String email);

    Optional<User_Entity> findByStudentId(String studentId);

    boolean existsByUserId(String userId);

    User_Entity findByUserId(String userId);
}
