package SpringBoot.College_Management.Security_Section.Authenticators;

import SpringBoot.College_Management.Security_Section.DTOs.Login_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.Login_Response_DTO;
import SpringBoot.College_Management.Security_Section.Session.Session_Service;
import SpringBoot.College_Management.Security_Section.USER.User_Authentication_Service;
import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import SpringBoot.College_Management.Security_Section.USER.User_Repository;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Authentication_Service {

    private final AuthenticationManager authenticationManager;
    private final Jwt_Service jwtService;
    private final Student_Repository studentRepository;
    private final User_Authentication_Service userService;
    private final Session_Service sessionService;
    private final User_Repository userRepository;


    public Login_Response_DTO login(Login_DTO loginDto) { // generate access and refresh token while login
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

        User_Entity user = (User_Entity) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user,refreshToken);
        return new Login_Response_DTO(user.getUserId(), user.getRole().toString(), accessToken,refreshToken);
    }




    public Login_Response_DTO refreshToken(String refreshToken) { // generate new access and refresh token using refresh token
        String userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User_Entity user = userService.getUserById(userId);
        String accessToken =  jwtService.generateAccessToken(user);
        return new Login_Response_DTO(user.getUserId(), user.getRole().toString(), accessToken,refreshToken);
    }
}

