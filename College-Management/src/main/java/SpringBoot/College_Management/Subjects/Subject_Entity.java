package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Professors.Professor_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Subjects",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Subject_name_unique", columnNames = {"name"}),

        }
)
public class Subject_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Subject_Id;

    @Column(unique = true,nullable = false)
    private String name;


    @ManyToMany(mappedBy = "subjects",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Professor_Entity> professors;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject_Entity that = (Subject_Entity) o;
        return Objects.equals(Subject_Id, that.Subject_Id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Subject_Id, name);
    }
}
