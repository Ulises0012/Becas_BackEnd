package sv.org.arrupe.API_BackEnd.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return bcrypt.encode(rawPassword);  // Genera el hash con bcrypt
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Verifica la contraseña ingresada con bcrypt
        return bcrypt.matches(rawPassword, encodedPassword);
    }
}
