package SpringBoot.College_Management.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Student_Repository extends JpaRepository<Student_Entity,Long> {
//    boolean isExistByRollNo(String rollNo);
//    boolean isExistByEmail(String email);
}
