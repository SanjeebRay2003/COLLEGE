package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
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
        name = "Departments",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Department_name_unique", columnNames = {"name"}),
//                @UniqueConstraint(name = "course_unique", columnNames = {"course"})
        }
)
public class Department_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_Id;

    @Column(unique = true,nullable = false)
    private String name;
//    @Column(unique = true)
//    private Set<Course_Entity> course;


    // Mapping with student
//    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Student_Entity> students;


     //mapping with course
    @OneToMany(mappedBy = "department_entity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Course_Entity> course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department_Entity that = (Department_Entity) o;
        return Objects.equals(department_Id, that.department_Id) && Objects.equals(name, that.name) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department_Id, name, course);
    }
}
