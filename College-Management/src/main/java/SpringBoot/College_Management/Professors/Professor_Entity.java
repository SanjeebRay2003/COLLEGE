package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Subjects.Subject_Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Professors")
public class Professor_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professor_Id;
    private String name;
    private String email;
    private Integer contactNo;
    private LocalDate dateOfJoining;
    private Boolean isActive;

    @ManyToMany(mappedBy = "professor",cascade = CascadeType.ALL)
    private Set<Subject_Entity> subjects;

}
