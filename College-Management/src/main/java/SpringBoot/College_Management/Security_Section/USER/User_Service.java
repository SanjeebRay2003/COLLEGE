package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
@RequiredArgsConstructor
public class User_Service {

    private final User_Repository userRepository;
    private final Student_Repository studentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final Professor_Repository professorRepository;


    public void isExistByUserId(String userId) {
        boolean isExistById = userRepository.existsByUserId(userId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("User Not Found with id : " + userId);
    }


    public User_DTO getUserById(String userId) {
        User_Entity user = userRepository.findByUserId(userId);
        return modelMapper.map(user,User_DTO.class);
    }

    public List<User_DTO> getAllUser() {
        List<User_Entity> user = userRepository.findAll();
        return user.stream()
                .map(userEntity -> modelMapper.map(userEntity, User_DTO.class))
                .collect(Collectors.toList());
    }


//    public User_Student_DTO getStudentUserById(String userId) {
//        User_Entity user = userRepository.findByUserId(userId);
//        return modelMapper.map(user,User_Student_DTO.class);
//    }
//
//    public List<User_Student_DTO> getAllStudentUser() {
//        List<User_Entity> user = userRepository.findAll();
//        return user.stream()
//                .map(userEntity -> modelMapper.map(userEntity, User_Student_DTO.class))
//                .collect(Collectors.toList());
//    }

//    public User_Professor_DTO getProfessorUserById(String userId) {
//        User_Entity user = userRepository.findByUserId(userId);
//        return modelMapper.map(user,User_Professor_DTO.class);
//    }
//
//    public List<User_Professor_DTO> getAllProfessorUser() {
//        List<User_Entity> user = userRepository.findAll();
//        return user.stream()
//                .map(userEntity -> modelMapper.map(userEntity, User_Professor_DTO.class))
//                .collect(Collectors.toList());
//    }
//


    public User_Student_DTO updateStudentUser(String userId,User_DTO userDto) {
        isExistByUserId(userId);
        User_Entity user = modelMapper.map(userDto, User_Entity.class);

        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User_Entity userEntity = userRepository.findByUserId(userId);
        user.setStudentId(userEntity.getStudentId());
        user.setSecretCode(userEntity.getSecretCode());

        Optional<Student_Entity> studentEntity = studentRepository.findByStudentId(userEntity.getStudentId());
        user.setIsActive(studentEntity.get().getIsActive());

        Optional<User_Entity> existingEmail = userRepository.findByEmail(userDto.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Email already exist for another user " + userDto.getEmail());
        }

        User_Entity updateUser = userRepository.save(user);
        return modelMapper.map(updateUser,User_Student_DTO.class);
    }

    public User_Professor_DTO updateProfessorUser(String userId, User_DTO userDto) {
        isExistByUserId(userId);
        User_Entity user = modelMapper.map(userDto, User_Entity.class);
        Optional<User_Entity> existingEmail = userRepository.findByEmail(userDto.getEmail());
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User_Entity userEntity = userRepository.findByUserId(userId);
        user.setStudentId(userEntity.getStudentId());
        user.setSecretCode(userEntity.getSecretCode());

        Optional<Professor_Entity> professorEntity = professorRepository.findByProfessorId(userEntity.getProfessorId());
        user.setIsActive(professorEntity.get().getIsActive());

        if (existingEmail.isPresent() && !existingEmail.get().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Email already exist for another user " + userDto.getEmail());
        }

        User_Entity updateUser = userRepository.save(user);
        return modelMapper.map(updateUser, User_Professor_DTO.class);
    }

    public User_Student_DTO partialUpdateStudentUser(String userId, Map<String, Object> updates) {
        isExistByUserId(userId);
        User_Entity user = userRepository.findByUserId(userId);

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(User_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, user, value);
        });

        Optional<User_Entity> existingEmail = userRepository.findByEmail(user.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Email already exist for another User " + user.getEmail()+ " -> "+existingEmail.get().getUserId());
        }


        User_Entity updateRequiredField = userRepository.save(user);
        return modelMapper.map(updateRequiredField, User_Student_DTO.class);
    }


    public User_Professor_DTO partialUpdateProfessorUser(String userId, Map<String, Object> updates) {
        isExistByUserId(userId);
        User_Entity user = userRepository.findByUserId(userId);

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(User_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, user, value);
        });

        Optional<User_Entity> existingEmail = userRepository.findByEmail(user.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getUserId().equals(user.getStudentId())) {
            throw new RuntimeException("Email already exist for another User " + user.getEmail()+ " -> "+existingEmail.get().getUserId());
        }


        User_Entity updateRequiredField = userRepository.save(user);
        return modelMapper.map(updateRequiredField, User_Professor_DTO.class);
    }
}
