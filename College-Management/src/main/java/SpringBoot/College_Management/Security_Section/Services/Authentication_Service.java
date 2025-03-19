package SpringBoot.College_Management.Security_Section.Services;

import SpringBoot.College_Management.Security_Section.DTOs.Login_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.Login_Response_DTO;
import SpringBoot.College_Management.Security_Section.User_Entity;
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
    private final User_Service userService;



    public Login_Response_DTO login(Login_DTO loginDto) { // generate access and refresh token while login
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        User_Entity user = (User_Entity) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new Login_Response_DTO(user.getId(), user.getRole(), accessToken,refreshToken);
    }

    public Login_Response_DTO refreshToken(String refreshToken) { // generate new access and refresh token using refresh token
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User_Entity user = userService.getUserById(userId);
        String accessToken =  jwtService.generateAccessToken(user);
        return new Login_Response_DTO(user.getId(), user.getRole(), accessToken,refreshToken);
    }
}

