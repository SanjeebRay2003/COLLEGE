//package SpringBoot.College_Management.Logger_Section.Audit_Controller;
//
//import SpringBoot.College_Management.Students.Student_Entity;
//import jakarta.persistence.EntityManagerFactory;
//import org.hibernate.envers.AuditReader;
//import org.hibernate.envers.AuditReaderFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping(path = "/audits")
//public class Audit_Controller {
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//
//    @GetMapping(path ="/posts/{postId}")
//    List<Student_Entity> getStudentRevisions(@PathVariable Long studentId){
//        AuditReader reader= AuditReaderFactory.get(entityManagerFactory.createEntityManager());
//        List<Number> revisions= reader.getRevisions(Student_Entity.class,studentId);
//        return revisions
//                .stream()
//                .map(revisionNumber-> reader.find(Student_Entity.class,studentId,revisionNumber))
//                .collect(Collectors.toList());
//    }
//}
