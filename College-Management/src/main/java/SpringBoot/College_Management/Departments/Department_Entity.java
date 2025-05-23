package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Professors.Professor_Entity;
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
        name = "Departments",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Department_name_unique", columnNames = {"name"}),
//                @UniqueConstraint(name = "course_unique", columnNames = {"course"})
        }
)
public class Department_Entity {

    @Id
    private String departmentId;

    @Column(unique = true,nullable = false)
    private String department;

     // department and course mapping
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Course_Entity> course;

    @OneToOne
    private Professor_Entity HOD;

    @OneToOne
    private Professor_Entity Dean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department_Entity that = (Department_Entity) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(department, that.department) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, department, course);
    }
}
