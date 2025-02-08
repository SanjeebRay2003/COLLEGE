package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Professors.Professor_Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "Subjects")
public class Subject_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Subject_Id;

    private String subject_Name;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subjects_of_Professors",
            joinColumns = @JoinColumn(name = "Professor_Id"),
            inverseJoinColumns = @JoinColumn(name = "subject_Id"))
    private Set<Professor_Entity> professor;
}
