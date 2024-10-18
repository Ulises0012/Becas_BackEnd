package sv.org.arrupe.API_BackEnd.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.security.JwtTokenProvider;
import sv.org.arrupe.API_BackEnd.security.UserPrincipal;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.logging.Logger;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;
    private SecretKeySpec jwtSigningKey;
    private static final Logger logger = Logger.getLogger(JwtTokenProviderTest.class.getName());

    @Mock
    private Authentication authentication;

    private UserPrincipal userPrincipal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Usar una clave de al menos 512 bits (64 caracteres para HmacSHA512)
        String secretKey = "EstaEsUnaClaveSecretaDe512BitsQueCumpleConElRequisitoDelAlgoritmoHS512";
        jwtSigningKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");

        // Inicializar JwtTokenProvider directamente con la clave y el tiempo de expiración
        jwtTokenProvider = new JwtTokenProvider(jwtSigningKey, 10000); // Expiración de 10 segundos

        // Crear un objeto Usuario para inicializar UserPrincipal
        Usuario usuario = new Usuario();
        usuario.setCarnet("carnet123");
        usuario.setPassword("password");
        usuario.setRol(Usuario.Rol.ESTUDIANTE);

        // Inicializar UserPrincipal con el objeto Usuario
        userPrincipal = new UserPrincipal(usuario);

        // Configurar el comportamiento simulado de authentication.getPrincipal()
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
    }

    @Test
    void testGenerateToken() {
        // Generar un token real simulando la firma con la clave secreta
        String token = jwtTokenProvider.generateToken(authentication);
        assertNotNull(token, "El token no debe ser nulo");
    }

    @Test
    void testValidateToken() {
        // Generar un token y validarlo
        String token = jwtTokenProvider.generateToken(authentication);
        assertTrue(jwtTokenProvider.validateToken(token), "El token debería ser válido");
    }

    @Test
    void testGetCarnetFromJWT() {
        // Generar un token y extraer el carnet desde el JWT
        String token = jwtTokenProvider.generateToken(authentication);
        String carnet = jwtTokenProvider.getCarnetFromJWT(token);
        assertEquals("carnet123", carnet, "El carnet debería coincidir");
    }

    @Test
    void testExpiredToken() {
        // Simular la expiración de un token generando un JWT con fecha pasada
        String expiredToken = Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis() - 10000)) // Fecha en el pasado
                .setExpiration(new Date(System.currentTimeMillis() - 5000)) // Expirado hace 5 segundos
                .signWith(jwtSigningKey, SignatureAlgorithm.HS512)
                .compact();

        assertFalse(jwtTokenProvider.validateToken(expiredToken), "El token debería ser inválido por expiración");
    }

    // Nuevo método para imprimir el token y utilizarlo en Postman
    @Test
    void generateTokenForPostman() {
        // Generar el token
        String token = jwtTokenProvider.generateToken(authentication);
        
        // Usar el Logger en lugar de System.out.println para imprimir el token
        logger.info("Token generado para Postman: " + token);
        
        // Asegurarte de que no sea nulo
        assertNotNull(token, "El token no debe ser nulo");
    }
}
