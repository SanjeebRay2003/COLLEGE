package SpringBoot.College_Management.Security_Section.Configuration;

import SpringBoot.College_Management.Security_Section.Filter.Jwt_Authentication_Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static SpringBoot.College_Management.Security_Section.Enums.Roles.ADMIN;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Web_Security_Configuration {

    private final Jwt_Authentication_Filter jwtAuthenticationFilter;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/error", "/authentication/**", "/home.html").permitAll()
                                .requestMatchers("/assign/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/students/**", "/authentication/**", "/professors/**",
                                        "/departments/**", "/courses/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/students/**", "/authentication/**", "/professors/**",
                                        "/departments/**", "/courses/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.PUT, "/students/**", "/authentication/**", "/professors/**",
                                        "/departments/**", "/courses/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.PATCH, "/students/**", "/authentication/**", "/professors/**",
                                        "/departments/**", "/courses/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE, "/students/**", "/authentication/**", "/professors/**",
                                        "/departments/**", "/courses/**").hasRole(ADMIN.name())

//
                                .anyRequest().authenticated())

                .csrf(csrfConfig -> csrfConfig.disable()) // it disable to use csrf token
                .sessionManagement(sessionConfig ->  // it disable to use jsessionId (STATELESS)
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());


        return httpSecurity.build();

    }

    @Bean
        // Authentication manager to handle login request
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//    @Bean
//        // Creating our own inMemory user details
//    UserDetailsService inMemoryUserDetails() {
//        // user
//        UserDetails user = User
//                .withUsername("aman")
//                .password(passwordEncoder().encode("aman"))
//                .roles("USER")
//                .build();
//
//        // admin
//        UserDetails admin = User
//                .withUsername("sanjeeb")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }


}

