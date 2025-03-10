//package SpringBoot.College_Management.Semesters;
//
//
//import SpringBoot.College_Management.Courses.Course_Entity;
//import SpringBoot.College_Management.Subjects.Subject_Entity;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Objects;
//import java.util.Set;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "Semesters")
//public class Semester_Entity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String semester;
//
//    // course and semester mapping
//    @ManyToMany(mappedBy = "semesters",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Course_Entity> courses;
//
//    // Semester and subject mapping
//    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Subject_Entity> subjects;
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Semester_Entity semester1 = (Semester_Entity) o;
//        return Objects.equals(id, semester1.id) && Objects.equals(semester, semester1.semester);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, semester);
//    }
//
//
//}
