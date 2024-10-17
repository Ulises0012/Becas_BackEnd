package sv.org.arrupe.API_BackEnd.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class JwtTestUtil {

    public static User createTestUser() {
        return new User("testuser", "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
