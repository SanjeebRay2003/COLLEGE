package SpringBoot.College_Management.Security_Section.USER;

import SpringBoot.College_Management.Security_Section.Enums.Roles;
import SpringBoot.College_Management.Security_Section.Utils.Permission_Mapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User_Entity implements UserDetails {

    @Id
    private String userId;
    private String studentId;
    private String professorId;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Roles> role;

    private String secretCode;
    private Boolean isActive;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        role.forEach(
                roles2 -> {
                    Set<SimpleGrantedAuthority> permissions = Permission_Mapping.getAuthoritiesForRole(roles2);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + roles2.name()));
                }
        );

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
