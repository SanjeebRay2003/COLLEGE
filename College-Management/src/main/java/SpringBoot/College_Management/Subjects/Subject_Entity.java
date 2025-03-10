package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Professors.Professor_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
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
        name = "Subjects",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Subject_name_unique", columnNames = {"name"}),

        }
)
public class Subject_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Subject_Id;


    @Column(unique = true,nullable = false)
    private String subject;


    // subject and professor mapping
    @ManyToMany(mappedBy = "subjects",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Professor_Entity> professors;

    @ManyToOne
    @JsonIgnore
    private Course_Entity course;

    // subject and semester mapping
//    @ManyToOne
//    @JsonIgnore
//    private Semester_Entity semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject_Entity that = (Subject_Entity) o;
        return Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subject);
    }



    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Subject_Entity that = (Subject_Entity) o;
//        return Objects.equals(Subject_Id, that.Subject_Id) && Objects.equals(name, that.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(Subject_Id, name);
//    }
}
