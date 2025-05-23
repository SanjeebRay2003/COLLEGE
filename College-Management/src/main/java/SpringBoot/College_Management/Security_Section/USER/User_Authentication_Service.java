package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
import SpringBoot.College_Management.Security_Section.DTOs.SignUp_DTO;
import SpringBoot.College_Management.Security_Section.Enums.Roles;
import SpringBoot.College_Management.Security_Section.USER.Verification.Verification_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import SpringBoot.College_Management.Students.Student_Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service     // i comment it for temporary to use inMemory user details like username and password
@RequiredArgsConstructor
public class User_Authentication_Service implements UserDetailsService {

    private final Student_Repository studentRepository;
    private final Professor_Repository professorRepository;
    private final Student_Service studentService;
    private final User_Repository userStudentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final User_Repository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // here username consider as Email
        return userStudentRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found with email " + username));
    }


    public User_Entity getUserById(String userId) {
        return userStudentRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id " + userId));
    }

    public User_Entity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public User_Student_DTO studentSignUp(SignUp_DTO sign_Up) {

        Optional<User_Entity> user = userStudentRepository.findByEmail(sign_Up.getEmail());

        if (user.isPresent()){
            throw new BadCredentialsException("User already exist with Email " +sign_Up.getEmail());
        }
        User_Entity toSaveUser = modelMapper.map(sign_Up, User_Entity.class);

        long count = userStudentRepository.count() + 1;
        String customId = "USER_" + String.format("%d", count);
        toSaveUser.setUserId(customId);

        toSaveUser.setRole(Collections.singleton(Roles.PROFESSOR));

        toSaveUser.setPassword(passwordEncoder.encode(toSaveUser.getPassword())); // encode the password
        User_Entity saveUser = userStudentRepository.save(toSaveUser);
        return modelMapper.map(saveUser, User_Student_DTO.class);
    }

    public User_Professor_DTO professorSignUp(SignUp_DTO sign_Up) {

        Optional<User_Entity> user = userStudentRepository.findByEmail(sign_Up.getEmail());

        if (user.isPresent()){
            throw new BadCredentialsException("User already exist with Email " +sign_Up.getEmail());
        }
        User_Entity toSaveUser = modelMapper.map(sign_Up, User_Entity.class);

        long count = userStudentRepository.count() + 1;
        String customId = "USER_" + String.format("%d", count);
        toSaveUser.setUserId(customId);

        toSaveUser.setPassword(passwordEncoder.encode(toSaveUser.getPassword())); // encode the password
        User_Entity saveUser = userStudentRepository.save(toSaveUser);
        return modelMapper.map(saveUser, User_Professor_DTO.class);
    }



    public User_Student_DTO verifyUserAsStudent(Verification_DTO verificationDto) {

        Optional<Student_Entity> studentEntity = studentRepository.findByEmail(verificationDto.getEmail());
        Optional<User_Entity> userEntity = userStudentRepository.findByEmail(verificationDto.getEmail());

       if(!studentRepository.existsByEmail(verificationDto.getEmail())){
           throw new ResourceNotFound("Student not found with email "+verificationDto.getEmail());
        }

        if(!userStudentRepository.existsByEmail(verificationDto.getEmail())){
            throw new ResourceNotFound("User not found with email "+verificationDto.getEmail());
        }

        return userEntity.flatMap(user1 -> studentEntity.map(student1 -> {

        if (Objects.equals(user1.getEmail(), student1.getEmail()) &&
                Objects.equals(verificationDto.getSecretCode(), student1.getSecretCode())) {
                user1.setStudentId(student1.getStudentId());
                user1.setSecretCode(student1.getSecretCode());
                user1.setIsActive(student1.getIsActive());
            userStudentRepository.save(user1);
            }else {
            throw new BadCredentialsException("User Email and User SecretCode are not Matched");
        }


        return modelMapper.map(user1, User_Student_DTO.class);
       } )).orElseThrow(() ->new BadCredentialsException("UnAuthorized"));


    }

    public User_Professor_DTO verifyUserAsProfessor(Verification_DTO verificationDto) {

        Optional<Professor_Entity> professorEntity = professorRepository.findByEmail(verificationDto.getEmail());
        Optional<User_Entity> userEntity = userRepository.findByEmail(verificationDto.getEmail());

        if(!professorRepository.existsByEmail(verificationDto.getEmail())){
            throw new ResourceNotFound("Professor not found with email "+verificationDto.getEmail());
        }

        if(!userRepository.existsByEmail(verificationDto.getEmail())){
            throw new ResourceNotFound("User not found with email "+verificationDto.getEmail());
        }

        return userEntity.flatMap(user1 -> professorEntity.map(professor -> {

            if (Objects.equals(user1.getEmail(), professor.getEmail()) &&
                    Objects.equals(verificationDto.getSecretCode(), professor.getSecretCode())) {
                user1.setProfessorId(professor.getProfessorId());
                user1.setSecretCode(professor.getSecretCode());
                user1.setIsActive(professor.getIsActive());
                userRepository.save(user1);
            }else {
                throw new BadCredentialsException("User Email and User SecretCode are not Matched");
            }

            return modelMapper.map(user1, User_Professor_DTO.class);
        } )).orElseThrow(() ->new BadCredentialsException("UnAuthorized"));

    }


}







