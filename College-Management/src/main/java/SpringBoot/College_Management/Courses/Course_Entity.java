package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student_Entity> students;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    @JsonIgnore
    private Department_Entity department_entity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course_Entity course = (Course_Entity) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
