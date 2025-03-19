package SpringBoot.College_Management.Security_Section.Services;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Security_Section.DTOs.SignUp_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.User_DTO;
import SpringBoot.College_Management.Security_Section.User_Entity;
import SpringBoot.College_Management.Security_Section.User_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service     // i comment it for temporary to use inMemory user details like username and password
@RequiredArgsConstructor
public class User_Service implements UserDetailsService {

    private final User_Repository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // here username consider as Email
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found with email "+username));
    }

    public User_Entity getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id "+userId));
    }

    public User_Entity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);

    }

    public User_DTO signUp(SignUp_DTO sign_Up) {
        Optional<User_Entity> user = userRepository.findByEmail(sign_Up.getEmail());
        if (user.isPresent()){
            throw new BadCredentialsException("User allready exist with Email " +sign_Up.getEmail());
        }
        User_Entity toSaveUser = modelMapper.map(sign_Up,User_Entity.class);
        toSaveUser.setPassword(passwordEncoder.encode(toSaveUser.getPassword())); // encode the password
        User_Entity saveUser = userRepository.save(toSaveUser);
        return modelMapper.map(saveUser,User_DTO.class);
    }


    public User_Entity save(User_Entity newUser) {
        return userRepository.save(newUser);
    }
}

