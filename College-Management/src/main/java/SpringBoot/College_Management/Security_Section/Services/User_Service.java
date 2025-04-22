package SpringBoot.College_Management.Security_Section.Services;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
import SpringBoot.College_Management.Security_Section.DTOs.SignUp_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.User_DTO;
//import SpringBoot.College_Management.Security_Section.Owner_Details.Owner_Of_Entity;
//import SpringBoot.College_Management.Security_Section.Owner_Details.Owner_Of_Entity;
import SpringBoot.College_Management.Security_Section.User_Entity;
import SpringBoot.College_Management.Security_Section.User_Repository;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import SpringBoot.College_Management.Students.Student_Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service     // i comment it for temporary to use inMemory user details like username and password
@RequiredArgsConstructor
public class User_Service implements UserDetailsService {

    private final Student_Repository studentRepository;
    private final Professor_Repository professorRepository;
    private final Student_Service studentService;
    private final User_Repository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // here username consider as Email
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found with email " + username));
    }

    public User_Entity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id " + userId));
    }

    public User_Entity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public User_DTO signUp(SignUp_DTO sign_Up) {
        Optional<User_Entity> user = userRepository.findByEmail(sign_Up.getEmail());
        if (user.isPresent()){
            throw new BadCredentialsException("User already exist with Email " +sign_Up.getEmail());
        }
        User_Entity toSaveUser = modelMapper.map(sign_Up,User_Entity.class);
        toSaveUser.setPassword(passwordEncoder.encode(toSaveUser.getPassword())); // encode the password
        User_Entity saveUser = userRepository.save(toSaveUser);
        return modelMapper.map(saveUser,User_DTO.class);
    }


//    public User_DTO signUp(SignUp_DTO sign_Up) {
//        Optional<User_Entity> user = userRepository.findByEmail(sign_Up.getEmail());
//        if (user.isPresent()){
//            throw new BadCredentialsException("User already exist with Email " +sign_Up.getEmail());
//        }
//
//        User_Entity toSaveUser = modelMapper.map(sign_Up,User_Entity.class);
//        toSaveUser.setPassword(passwordEncoder.encode(toSaveUser.getPassword())); // encode the password
//
//        Student_Entity student = studentRepository.findByEmail(toSaveUser.getEmail());
//        Professor_Entity professor = professorRepository.findByEmail(toSaveUser.getEmail());
//        if (toSaveUser.getRole().equals("ROLE_STUDENT")){
//            if (student.getEmail().equals(toSaveUser.getEmail())){
//                toSaveUser.setStudentId(student.getStudentId());
//                userRepository.save(toSaveUser);
//            }
//            else {
//                throw new ResourceNotFound("email not matched");
//            }
//        }
//        else if (toSaveUser.getRole().equals("ROLE_PROFESSOR")){
//            if (professor.getEmail().equals(toSaveUser.getEmail())){
//                toSaveUser.setProfessorId(professor.getProfessorId());
//                userRepository.save(toSaveUser);
//                }
//            else {
//                throw new ResourceNotFound("email not matched");
//            }
//        }
//
//        User_Entity saveUser = userRepository.save(toSaveUser);
//        return modelMapper.map(saveUser,User_DTO.class);
//    }
//
//
//    public User_Entity save(User_Entity newUser) {
//        return userRepository.save(newUser);
//    }


//    public User_DTO updateUser(Long userId, Map<String, Object> updates) {
//        isExistById(userId);
//        User_Entity user = userRepository.findById(userId).get();
//
//        updates.forEach((field, value) -> {
//            Field fieldToUpdate = findRequiredField(User_Entity.class, field);
//            fieldToUpdate.setAccessible(true);
//            ReflectionUtils.setField(fieldToUpdate, user, value);
//        });
//
//        User_Entity updateRequiredField = userRepository.save(user);
//        return modelMapper.map(updateRequiredField, User_DTO.class);
//    }


//    public void isExistById(Long userId) {
//        boolean isExistById = userRepository.existsById(userId);// checks the id is present or not
//        if (!isExistById)
//            throw new ResourceNotFound("User Not Found with id : " + userId);
//
//    }
}







