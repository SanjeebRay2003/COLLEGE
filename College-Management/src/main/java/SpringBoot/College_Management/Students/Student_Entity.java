package SpringBoot.College_Management.Students;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "Students")
public class Student_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_Id;
    private String rollNo;
    private String name;
    private String semester;
    private String email;
    private String contactNo;
    private LocalDate dateOfAdmission;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student_Entity that = (Student_Entity) o;
//        return Objects.equals(getStudent_Id(), that.getStudent_Id()) && Objects.equals(getRollNo(), that.getRollNo());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getStudent_Id(), getRollNo());
//    }
}
