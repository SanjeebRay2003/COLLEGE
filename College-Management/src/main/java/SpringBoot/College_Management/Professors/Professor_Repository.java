package SpringBoot.College_Management.Professors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Professor_Repository extends JpaRepository<Professor_Entity,Long> {
    boolean existsByEmail(String email);
    boolean existsByContactNo(String contactNo);
    boolean existsByName(String name);
    void deleteByProfessorIdAndName(Long id,String professor);
    Optional<Professor_Entity> findByProfessorIdAndName(Long id,String name);

}
