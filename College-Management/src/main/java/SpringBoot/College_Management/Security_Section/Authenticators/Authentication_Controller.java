package SpringBoot.College_Management.Security_Section.Authenticators;

import SpringBoot.College_Management.Security_Section.DTOs.Login_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.Login_Response_DTO;
import SpringBoot.College_Management.Security_Section.DTOs.SignUp_DTO;
import SpringBoot.College_Management.Security_Section.USER.User_Authentication_Service;
import SpringBoot.College_Management.Security_Section.USER.User_DTO;
import SpringBoot.College_Management.Security_Section.USER.User_Professor_DTO;
import SpringBoot.College_Management.Security_Section.USER.User_Student_DTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/authentication")
@RequiredArgsConstructor
public class Authentication_Controller {

    private final User_Authentication_Service userService;
    private final Authentication_Service authenticationService;

    @Value("${deploy.env}")
    private  String deployEnv;

    @PostMapping(path = "/student/signUp")
    public ResponseEntity<User_Student_DTO> studentSignUp(@RequestBody SignUp_DTO sign_Up){
        User_Student_DTO userDto =  userService.studentSignUp(sign_Up);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping(path = "/professor/signUp")
    public ResponseEntity<User_Professor_DTO> professorSignUp(@RequestBody SignUp_DTO sign_Up){
        User_Professor_DTO userDto =  userService.professorSignUp(sign_Up);
        return ResponseEntity.ok(userDto);
    }



    @PostMapping(path = "/login")
    public ResponseEntity<Login_Response_DTO> login (@RequestBody Login_DTO loginDto , HttpServletRequest request, HttpServletResponse response){
        Login_Response_DTO loginResponseDto = authenticationService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken()); // save the refresh token in the cookies
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(path = "/refresh") // we got the refresh token from the cookies
    public ResponseEntity<Login_Response_DTO> refresh(HttpServletRequest request){
        String refreshToken =  Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("refreshToken not found inside the Cookies"));

        Login_Response_DTO loginResponseDto = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }



}
