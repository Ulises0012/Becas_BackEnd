package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.UsuarioRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Autentica al usuario verificando el carnet y la contraseña.
     */
    public Optional<Usuario> autenticar(String carnet, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByCarnet(carnet);
        if (usuario.isPresent() && verificarPassword(password, usuario.get().getPassword())) {
            return usuario;
        }
        return Optional.empty();
    }

    /**
     * Verifica si la contraseña ingresada coincide con la almacenada (hasheada).
     */
    private boolean verificarPassword(String passwordIngresada, String passwordAlmacenada) {
        String hashedPassword = hashPassword(passwordIngresada);
        return hashedPassword.equals(passwordAlmacenada);
    }

    /**
     * Hashea la contraseña utilizando SHA-256.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    /**
     * Convierte un array de bytes a su representación hexadecimal.
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Obtiene al usuario actualmente autenticado desde el contexto de seguridad.
     */
    public Optional<Usuario> obtenerUsuarioActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        String carnet = authentication.getName(); // Nombre del usuario autenticado (carnet).
        return usuarioRepository.findByCarnet(carnet);
    }
}
