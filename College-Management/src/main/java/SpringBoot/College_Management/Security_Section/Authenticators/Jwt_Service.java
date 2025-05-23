package SpringBoot.College_Management.Security_Section.Authenticators;

import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class Jwt_Service {

    @Value("${jwt.secretkey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Generating token
    public String generateAccessToken(User_Entity user){
        return   Jwts.builder()
                .subject(user.getUserId().toString())
                .claim("email",user.getEmail())
                .claim("role", user.getRole().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60*60))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User_Entity user){
        return   Jwts.builder()
                .subject(user.getUserId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();
    }

    // Check token is valid or not and get the user
    public String getUserIdFromToken(String token){
        Claims claims =Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return String.valueOf(claims.getSubject());
    }

}

