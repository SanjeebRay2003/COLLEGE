package SpringBoot.College_Management.Security_Section.USER.Sorted_User;


import SpringBoot.College_Management.Security_Section.Enums.Roles;
import SpringBoot.College_Management.Security_Section.USER.User_DTO;
import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import SpringBoot.College_Management.Security_Section.USER.User_Repository;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Sort_User_Service {

    private final User_Repository userRepository;
    private final ModelMapper modelMapper;


    public List<User_DTO> getSortedUserByIsActive(Boolean active) {
        List<User_Entity> user = userRepository.findByIsActive(active);
        return user
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, User_DTO.class))
                .collect(Collectors.toList());
    }

    public List<User_DTO> getSortedUserByName(String name) {
        List<User_Entity> user = userRepository.findByName(name);
        return user
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, User_DTO.class))
                .collect(Collectors.toList());
    }

    public List<User_DTO> getSortedUserByRole(Roles role) {
        List<User_Entity> user = userRepository.findByRole(role);
        return user
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, User_DTO.class))
                .collect(Collectors.toList());
    }
}
