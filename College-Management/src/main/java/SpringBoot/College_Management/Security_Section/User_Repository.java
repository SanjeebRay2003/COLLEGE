package SpringBoot.College_Management.Security_Section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_Repository extends JpaRepository<User_Entity,Long> {

    Optional<User_Entity> findByEmail(String username);


}
