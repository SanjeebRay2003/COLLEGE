package SpringBoot.College_Management.Security_Section.Owner_Details;

import SpringBoot.College_Management.Security_Section.DTOs.User_DTO;
import SpringBoot.College_Management.Security_Section.Services.User_Service;
import SpringBoot.College_Management.Security_Section.User_Entity;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Owner_Of_Entity {

    private final Student_Service studentService;



    public boolean isOwner (String email){

            User_Entity user = (User_Entity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Student_DTO student = studentService.getStudentByEmail(email);
            return student.getEmail().equals(user.getEmail());
}
}
