package SpringBoot.College_Management.Semesters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Semester_Repository extends JpaRepository<Semester_Entity,Long> {
    boolean existsBySemester(String semester);
}
