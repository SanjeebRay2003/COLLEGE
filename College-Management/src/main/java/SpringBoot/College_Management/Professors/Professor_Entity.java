package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Subjects.Subject_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Professors",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Email_unique", columnNames = {"email"}),
//                @UniqueConstraint(name = "Contact_unique", columnNames = {"contactNo"})
        }
)
public class Professor_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professor_Id;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String contactNo;
    private LocalDate dateOfJoining;
    private Boolean isActive;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subjects_of_Professors",
            joinColumns = @JoinColumn(name = "subject_Id"),
            inverseJoinColumns = @JoinColumn(name = "professor_Id"))
    @JsonIgnore
    private Set<Subject_Entity> subjects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor_Entity that = (Professor_Entity) o;
        return Objects.equals(professor_Id, that.professor_Id) && Objects.equals(email, that.email) && Objects.equals(contactNo, that.contactNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professor_Id, email, contactNo);
    }
}
