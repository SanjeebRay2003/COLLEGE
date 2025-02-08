package SpringBoot.College_Management.Departments;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "Departments")
public class Department_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_Id;
    private String name;
    private String course;
}
