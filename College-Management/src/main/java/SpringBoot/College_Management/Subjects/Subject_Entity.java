package SpringBoot.College_Management.Subjects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Subjects")
public class Subject_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Subject_Id;

    private String subject_Name;
}
