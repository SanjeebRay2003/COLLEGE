package SpringBoot.College_Management.Security_Section.Session;

import SpringBoot.College_Management.Security_Section.Entities.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Session_Repository extends JpaRepository<Session_Entity,Long> {
    List<Session_Entity> findByUser(User_Entity user);

    Optional<Session_Entity> findByRefreshToken(String refreshToken);
}
