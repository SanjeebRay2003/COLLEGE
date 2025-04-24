package SpringBoot.College_Management.Security_Section.Session;

import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Session")
public class Session_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;

    private String refreshToken;

    @ManyToOne
    private User_Entity user;

    @CreationTimestamp
    private LocalDateTime lastUsedAt;
}
