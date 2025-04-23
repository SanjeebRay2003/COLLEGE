//package SpringBoot.College_Management.Security_Section.Verification;
//
//import SpringBoot.College_Management.Security_Section.DTOs.User_DTO;
//import SpringBoot.College_Management.Security_Section.Entities.User_Entity;
//import SpringBoot.College_Management.Security_Section.User_Repository;
//import SpringBoot.College_Management.Students.Student_Entity;
//import SpringBoot.College_Management.Students.Student_Repository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class Verification_Service {
//    private final User_Repository userRepository;
//    private final Student_Repository studentRepository;
//    private final ModelMapper modelMapper;
//
//    public  Verification_DTO verifyRoleWithUser(String userId, String studentId) {
//        Optional<User_Entity> userEntity = userRepository.findByUserId(userId);
//        Optional<Student_Entity> studentEntity = studentRepository.findByStudentId(studentId);
//
//       return userEntity.flatMap(user1 -> studentEntity.map(student1 -> {
//           if (student1.getSecretCode().equals(user1.getSecretCode())){
//               user1.setStudentId(student1.getStudentId());
//               userRepository.save(user1);
//           }
//           return modelMapper.map(user1,Verification_DTO.class);
//       })).orElseThrow(() -> new BadCredentialsException("Your email and secret code are invalid"));
//
//    }
//}
