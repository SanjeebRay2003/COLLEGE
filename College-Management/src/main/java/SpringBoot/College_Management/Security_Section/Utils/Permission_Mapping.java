package SpringBoot.College_Management.Security_Section.Utils;

import SpringBoot.College_Management.Security_Section.Enums.Permissions;
import SpringBoot.College_Management.Security_Section.Enums.Roles;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static SpringBoot.College_Management.Security_Section.Enums.Permissions.*;
import static SpringBoot.College_Management.Security_Section.Enums.Roles.*;

public class Permission_Mapping {

    private static final Map<Roles, Set<Permissions>> map = Map.of(
            STUDENT, Set.of(USER_VIEW, STUDENT_VIEW, PROFESSOR_VIEW, DEPARTMENT_VIEW, COURSE_VIEW, SUBJECT_VIEW),
            PROFESSOR, Set.of(USER_VIEW, STUDENT_VIEW, PROFESSOR_VIEW, DEPARTMENT_VIEW, COURSE_VIEW, SUBJECT_VIEW),
//            USER, Set.of(USER_VIEW, STUDENT_VIEW, PROFESSOR_VIEW, DEPARTMENT_VIEW, COURSE_VIEW, SUBJECT_VIEW),
            ADMIN, Set.of(USER_VIEW,
                    USER_CREATE,
                    USER_UPDATE,
                    USER_DELETE,

                    STUDENT_VIEW,
                    STUDENT_CREATE,
                    STUDENT_UPDATE,
                    STUDENT_DELETE,

                    PROFESSOR_VIEW,
                    PROFESSOR_CREATE,
                    PROFESSOR_UPDATE,
                    PROFESSOR_DELETE,

                    COURSE_VIEW,
                    COURSE_CREATE,
                    COURSE_UPDATE,
                    COURSE_DELETE,

                    DEPARTMENT_VIEW,
                    DEPARTMENT_CREATE,
                    DEPARTMENT_UPDATE,
                    DEPARTMENT_DELETE,

                    SUBJECT_VIEW,
                    SUBJECT_CREATE,
                    SUBJECT_UPDATE,
                    SUBJECT_DELETE
            )
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Roles role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}

