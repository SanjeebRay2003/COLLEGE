package SpringBoot.College_Management.Professors;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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

}
