package SpringBoot.College_Management.Security_Section.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Password_Encoder {
    @Bean
        // Password encoder to encode passwords
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
