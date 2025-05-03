//package SpringBoot.College_Management.Security_Section.Authenticators.Give_Permission;
//
//import SpringBoot.College_Management.Security_Section.USER.User_Service;
//import SpringBoot.College_Management.Security_Section.USER.User_Student_DTO;
//import SpringBoot.College_Management.Students.Student_Service;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
//@Component
//@RequiredArgsConstructor
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//
//    private final Student_Service studentService;
//    private final User_Service userService;
//
//    @Override
//    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
//        // You can fetch your student entity by ID here
//        User_Student_DTO user = userService.getUserById((String) targetId);
//
//        // Match logged in user ID with student.user.id
//        String studentId = (String) auth.getPrincipal();
//        return user != null && user.getStudentId().equals(studentId);
//    }
//
//    @Override
//    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
//        return false; // we’re not using this overload in this case
//    }
//}
