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
@Table(name = "Subjects")
public class Subject_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Subject_Id;

    private String subject_Name;


    @ManyToMany(mappedBy = "subjects",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Professor_Entity> professor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject_Entity that = (Subject_Entity) o;
        return Objects.equals(Subject_Id, that.Subject_Id) && Objects.equals(subject_Name, that.subject_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Subject_Id, subject_Name);
    }
}
