package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Security_Section.Enums.Roles;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
@RequiredArgsConstructor
public class User_Service {

    private final User_Repository userRepository;
    private final Student_Repository studentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public void isExistByUserId(String userId) {
        boolean isExistById = userRepository.existsByUserId(userId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("User Not Found with id : " + userId);
    }

    public User_Student_DTO getUserById(String userId) {
        User_Entity user = userRepository.findByUserId(userId);
        return modelMapper.map(user,User_Student_DTO.class);
    }

    public List<User_Student_DTO> getAllUser() {
        List<User_Entity> user = userRepository.findAll();
        return user.stream()
                .map(userEntity -> modelMapper.map(userEntity, User_Student_DTO.class))
                .collect(Collectors.toList());
    }

//    public User_Student_DTO updateUser(String userId, Map<String, Object> updates) {
//
//        isExistByUserId(userId);
//        User_Entity user = userRepository.findByUserId(userId);
//
//        updates.forEach((field, value) -> {
//            Field fieldToUpdate = findRequiredField(User_Entity.class, field);
//            fieldToUpdate.setAccessible(true);
//            ReflectionUtils.setField(fieldToUpdate, user, value);});
//
//        User_Entity updateRequiredField = userRepository.save(user);
//        return modelMapper.map(updateRequiredField, User_Student_DTO.class);
//
//    }

    public User_Student_DTO updateStudentUser(String userId,User_DTO userDto) {
        isExistByUserId(userId);
        User_Entity user = modelMapper.map(userDto, User_Entity.class);
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User_Entity updateUser = userRepository.save(user);
        return modelMapper.map(updateUser,User_Student_DTO.class);
    }

    public User_Professor_DTO updateProfessorUser(String userId, User_DTO userDto) {
        isExistByUserId(userId);
        User_Entity user = modelMapper.map(userDto, User_Entity.class);
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User_Entity updateUser = userRepository.save(user);
        return modelMapper.map(updateUser,User_Professor_DTO.class);
    }

}
