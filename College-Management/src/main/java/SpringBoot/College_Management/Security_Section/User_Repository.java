package SpringBoot.College_Management.Security_Section;

import SpringBoot.College_Management.Security_Section.Entities.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_Repository extends JpaRepository<User_Entity,String> {

    Optional<User_Entity> findByEmail(String username);


    Optional<User_Entity> findByUserId(String user);

    boolean existsByEmail(String email);

    Optional<User_Entity> findByStudentId(String studentId);
}
