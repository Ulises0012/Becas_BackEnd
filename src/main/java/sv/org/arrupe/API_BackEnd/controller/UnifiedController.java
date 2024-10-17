package sv.org.arrupe.API_BackEnd.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.EstudioSocioeconomico;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.UsuarioRepository;
import sv.org.arrupe.API_BackEnd.security.JwtTokenProvider;
import sv.org.arrupe.API_BackEnd.security.JwtAuthenticationFilter;
import sv.org.arrupe.API_BackEnd.service.EstudioSocioeconomicoService;

import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UnifiedController {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private EstudioSocioeconomicoService estudioSocioeconomicoService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        String carnet = loginRequest.get("carnet");
        String password = loginRequest.get("password");
        
        logger.info("Intento de inicio de sesión para carnet: {}", carnet);
        logger.debug("Datos recibidos - Carnet: {}, Password: {}", carnet, "*".repeat(password.length()));

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(carnet, password));
            
            logger.info("Autenticación exitosa para carnet: {}", carnet);
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            
            logger.info("JWT generado para carnet: {}", carnet);
            logger.debug("JWT generado: {}", jwt);
            
            Usuario user = userRepository.findByCarnet(carnet)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            logger.info("Inicio de sesión exitoso para carnet: {}, Rol: {}", carnet, user.getRol());
            
            return ResponseEntity.ok(Map.of(
                    "token", jwt,
                    "carnet", user.getCarnet(),
                    "rol", user.getRol()
            ));
        } catch (BadCredentialsException e) {
            logger.error("Credenciales inválidas para carnet: {}", carnet);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        } catch (AuthenticationException e) {
            logger.error("Error de autenticación para carnet: {}", carnet, e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Error de autenticación"));
        } catch (Exception e) {
            logger.error("Error inesperado durante la autenticación para carnet: {}", carnet, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        logger.info("Intento de registro para carnet: {}", usuario.getCarnet());
        logger.debug("Datos recibidos para registro: {}", usuario);

        if (userRepository.existsByCarnet(usuario.getCarnet())) {
            logger.warn("Intento de registro con carnet ya existente: {}", usuario.getCarnet());
            return ResponseEntity.badRequest().body(Map.of("error", "El carnet ya está en uso"));
        }

        usuario.setPassword(encoder.encode(usuario.getPassword()));
        Usuario result = userRepository.save(usuario);
        
        logger.info("Usuario registrado exitosamente con carnet: {}, Rol: {}", result.getCarnet(), result.getRol());
        
        return ResponseEntity.ok(Map.of(
                "message", "Usuario registrado exitosamente",
                "carnet", result.getCarnet()
        ));
    }

    @GetMapping("/estudiosocioeconomico")
    public ResponseEntity<List<EstudioSocioeconomico>> getAllEstudiosSocioeconomicos() {
        logger.info("Solicitando todos los estudios socioeconómicos");
        List<EstudioSocioeconomico> estudios = estudioSocioeconomicoService.getAllEstudiosSocioeconomicos();
        logger.info("Recuperados {} estudios socioeconómicos", estudios.size());
        return ResponseEntity.ok(estudios);
    }

    @GetMapping("/estudiosocioeconomico/{id}")
    public ResponseEntity<EstudioSocioeconomico> verEstudioSocioeconomico(@PathVariable Integer id) {
        logger.info("Solicitando estudio socioeconómico con ID: {}", id);
        EstudioSocioeconomico estudio = estudioSocioeconomicoService.verEstudioSocioeconomico(id);
        if (estudio != null) {
            logger.info("Estudio socioeconómico encontrado para ID: {}", id);
            return ResponseEntity.ok(estudio);
        } else {
            logger.warn("Estudio socioeconómico no encontrado con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/estudiosocioeconomico/{id}")
    public ResponseEntity<?> eliminarEstudioSocioeconomico(@PathVariable Integer id) {
        logger.info("Intento de eliminar estudio socioeconómico con ID: {}", id);
        try {
            estudioSocioeconomicoService.eliminarEstudioSocioeconomico(id);
            logger.info("Estudio socioeconómico eliminado exitosamente, ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error al eliminar estudio socioeconómico con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al eliminar el estudio socioeconómico"));
        }
    }

    @GetMapping("/usuario/{carnet}")
    public ResponseEntity<?> getUsuario(@PathVariable String carnet) {
        logger.info("Solicitando información del usuario con carnet: {}", carnet);
        try {
            Usuario usuario = userRepository.findByCarnet(carnet)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            logger.info("Usuario encontrado para carnet: {}", carnet);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            logger.error("Error al obtener información del usuario con carnet: {}", carnet, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Usuario no encontrado"));
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        logger.info("Procesando solicitud de logout");

        try {
            // Obtener el token del header
            String jwt = request.getHeader("Authorization");
            if (StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);

                // Invalidar el token
                jwtAuthenticationFilter.invalidateToken(jwt);

                // Limpiar el contexto de seguridad
                SecurityContextHolder.clearContext();

                logger.info("Logout exitoso - Token invalidado");
                return ResponseEntity.ok(Map.of("message", "Logout exitoso"));
            }

            logger.warn("Intento de logout sin token válido");
            return ResponseEntity.badRequest().body(Map.of("error", "No se proporcionó un token válido"));

        } catch (Exception e) {
            logger.error("Error durante el proceso de logout", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error durante el proceso de logout"));
        }
    }
}