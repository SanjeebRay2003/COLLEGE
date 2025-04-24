package SpringBoot.College_Management.Security_Section.Owner_Details;


import SpringBoot.College_Management.Professors.Professor_DTO;
import SpringBoot.College_Management.Professors.Professor_Service;
import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("owner_Of_Entity")
@RequiredArgsConstructor
public class Owner_Of_Entity {

    private final Student_Service studentService;
    private final Professor_Service professorService;

    public boolean isStudentOwner(String studentId) {

        User_Entity user = (User_Entity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student_DTO student = studentService.getStudentByStudentId(studentId);
        return student.getStudentId().equals(user.getStudentId());
    }

    public boolean isProfessorOwner(String professorId) {

        User_Entity user = (User_Entity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Professor_DTO professor = professorService.getProfessorByProfessorId(professorId);
        return professor.getProfessorId().equals(user.getProfessorId());
    }
}
