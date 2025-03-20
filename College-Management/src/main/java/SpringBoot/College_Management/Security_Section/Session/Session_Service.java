package SpringBoot.College_Management.Security_Section.Session;

import SpringBoot.College_Management.Security_Section.User_Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Session_Service {
    private final Session_Repository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User_Entity user, String refreshToken) {
        List<Session_Entity> userSession = sessionRepository.findByUser(user);

        if (userSession.size() == SESSION_LIMIT) {
            userSession.sort(Comparator.comparing(Session_Entity::getLastUsedAt));

            Session_Entity leastRecentUsedSession = userSession.getFirst();
            sessionRepository.delete(leastRecentUsedSession);

        }
        Session_Entity newSession = Session_Entity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);
    }


    public void validateSession(String refreshToken){
        Session_Entity session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session not found for refresh token : "+refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

}
