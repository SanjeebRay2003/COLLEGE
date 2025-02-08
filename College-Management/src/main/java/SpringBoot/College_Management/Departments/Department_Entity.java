package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Students.Student_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Departments")
public class Department_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_Id;

    private String name;
    @Column(unique = true)
    private String course;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student_Entity> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department_Entity that = (Department_Entity) o;
        return Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(course);
    }
}
