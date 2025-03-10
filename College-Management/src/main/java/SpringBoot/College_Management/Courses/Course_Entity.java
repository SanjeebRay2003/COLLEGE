package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Departments.Department_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Subjects.Subject_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Courses",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Department_name_unique", columnNames = {"name"}),
//                @UniqueConstraint(name = "course_unique", columnNames = {"course"})
        }
)
public class Course_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String course;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student_Entity> students;

    // course and department mapping
    @ManyToOne
    @JoinColumn(name = "departmentId")
    @JsonIgnore
    private Department_Entity department;

    private Integer years;


    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Subject_Entity> subjects;

    // course and semester mapping
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "Semesters_Of_Courses",
//            joinColumns = @JoinColumn(name = "Course_Id"),
//            inverseJoinColumns = @JoinColumn(name = "Semester_Id"))
//    @JsonIgnore
//    private Set<Semester_Entity> semesters;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course_Entity course = (Course_Entity) o;
        return Objects.equals(id, course.id) && Objects.equals(this.course, course.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course);
    }
}
