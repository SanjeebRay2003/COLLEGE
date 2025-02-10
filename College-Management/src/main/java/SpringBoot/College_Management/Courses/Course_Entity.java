package SpringBoot.College_Management.Courses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Courses",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Department_name_unique", columnNames = {"name"}),
//                @UniqueConstraint(name = "course_unique", columnNames = {"course"})
        }
)
public class Course_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;
}
