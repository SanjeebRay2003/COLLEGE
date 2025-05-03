package SpringBoot.College_Management.Professors;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface Professor_Repository extends JpaRepository<Professor_Entity,String> {
    boolean existsByEmail(String email);
    boolean existsByContactNo(String contactNo);
    boolean existsByName(String name);
    void deleteByProfessorIdAndName(String id,String professor);
    Optional<Professor_Entity> findByProfessorIdAndName(String id,String name);

    Optional<Professor_Entity> findByEmail(String email);


    boolean existsBySecretCode(String secretCode);

    boolean existsByProfessorId(String professorId);

    Optional<Professor_Entity> findByProfessorId(String professorId);


    // Sorting

    List<Professor_Entity> findBy(Sort sort);

    List<Professor_Entity> findByName(String name);

    List<Professor_Entity> findByIsActive(Boolean active);

    List<Professor_Entity> findByDateOfJoiningBetween(LocalDate startDate, LocalDate endDate);
}
