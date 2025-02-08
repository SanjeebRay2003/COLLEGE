package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Departments.Department_Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Students")
public class Student_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_Id;
    @Column(unique = true,nullable = false)
    private String rollNo;
    private String name;
    private String semester;
    @Column(unique = true,nullable = false)
    private String email;
    private String contactNo;
    private LocalDate dateOfAdmission;


    @ManyToOne
    @JoinColumn(name = "department_Id")
    private Department_Entity department;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student_Entity that = (Student_Entity) o;
        return Objects.equals(getRollNo(), that.getRollNo()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRollNo(), getEmail());
    }
}
