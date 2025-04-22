package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Students.Student_Entity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Professor_Repository extends JpaRepository<Professor_Entity,String> {
    boolean existsByEmail(String email);
    boolean existsByContactNo(String contactNo);
    boolean existsByName(String name);
    void deleteByProfessorIdAndName(String id,String professor);
    Optional<Professor_Entity> findByProfessorIdAndName(String id,String name);

    Professor_Entity findByEmail(String email);
    List<Professor_Entity> findBy(Sort sort);

    boolean existsBySecretCode(String secretCode);

    boolean existsByProfessorId(String professorId);
}
