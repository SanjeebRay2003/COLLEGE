package SpringBoot.College_Management.Courses;

//import SpringBoot.College_Management.Courses.Enums.Semester_Enum;
import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Subjects.Subject_Entity;
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
        name = "Courses",
        uniqueConstraints = {
//                @UniqueConstraint(name = "Department_name_unique", columnNames = {"name"}),
//                @UniqueConstraint(name = "course_unique", columnNames = {"course"})
        }
)
public class Course_Entity {
    @Id
    private String courseId;

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


    //     course and semester mapping
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "Semesters_Of_Courses",
//            joinColumns = @JoinColumn(name = "Course_Id"),
//            inverseJoinColumns = @JoinColumn(name = "Semester_Id"))
//    @JsonIgnore
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private Set<Semester_Enum> semesters;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Subject_Entity> subjects;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course_Entity course = (Course_Entity) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(this.course, course.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, course);
    }
}
