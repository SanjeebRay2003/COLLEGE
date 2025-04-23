package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Courses.Course_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Students",
        uniqueConstraints = {
//                @UniqueConstraint(name = "rollNo_unique", columnNames = {"rollNo"}),
//                @UniqueConstraint(name = "email_unique", columnNames = {"email"})
        }
)
public class Student_Entity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentId;
    @Column(unique = true,nullable = false)
    private String rollNo;
    private String name;
    private String semester;
    @Column(unique = true,nullable = false)
    private String email;
    private String contactNo;
    private LocalDate dateOfAdmission;
    //student and course mapping
    @ManyToOne
    @JoinColumn(name = "course")
    @JsonIgnore
    private Course_Entity course;

    private String secretCode;

//    @OneToOne
//    private User_Entity owner;




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
