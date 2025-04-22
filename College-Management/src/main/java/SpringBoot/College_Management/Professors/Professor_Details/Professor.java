package SpringBoot.College_Management.Professors.Professor_Details;

import SpringBoot.College_Management.Subjects.Subject_Entity;
import lombok.Data;

import java.util.Set;

@Data
public class Professor {

    private  String id;
    private String name;
    private Boolean isActive;
    private Set<Subject_Entity> subjects;
}
