package sv.org.arrupe.API_BackEnd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import sv.org.arrupe.API_BackEnd.security.JwtTokenProvider;
import sv.org.arrupe.API_BackEnd.security.SecurityTestConfig;
import sv.org.arrupe.API_BackEnd.service.UsuarioService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(AuthController.class)
@Import(SecurityTestConfig.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    private Authentication authentication;

    @BeforeEach
    void setUp() {
        // Simula una autenticación válida
        UserDetails userDetails = new User("test@test.com", "password123", Collections.emptyList());
        authentication = new UsernamePasswordAuthenticationToken(userDetails, "password123", userDetails.getAuthorities());

        // Configura el comportamiento del AuthenticationManager y JwtTokenProvider
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtTokenProvider.generateToken(authentication)).thenReturn("fake-jwt-token");
    }

    @Test
    void testLogin() throws Exception {
        // Cuerpo del request de prueba
        String loginRequest = "{\"username\":\"test@test.com\",\"password\":\"password123\"}";

        // Realiza la solicitud POST a /auth/login
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest))
                .andExpect(status().isOk()); // Verifica que el status sea 200 OK
    }
}
