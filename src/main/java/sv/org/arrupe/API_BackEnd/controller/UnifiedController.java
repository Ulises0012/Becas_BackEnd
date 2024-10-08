package sv.org.arrupe.API_BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.UsuarioRepository;
import sv.org.arrupe.API_BackEnd.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite peticiones desde cualquier origen
public class UnifiedController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> procesarLogin(@RequestParam String carnet,
                                         @RequestParam String password) {
        try {
            System.out.println("API: Recibida petición de login para carnet: " + carnet);
            Optional<Usuario> usuario = usuarioService.autenticar(carnet, password);
            
            if (usuario.isPresent()) {
                System.out.println("API: Login exitoso para carnet: " + carnet);
                return ResponseEntity.ok()
                    .body(Map.of(
                        "status", "success",
                        "message", "Login exitoso",
                        "carnet", carnet
                    ));
            } else {
                System.out.println("API: Login fallido para carnet: " + carnet);
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                        "status", "error",
                        "message", "Credenciales inválidas"
                    ));
            }
        } catch (Exception e) {
            System.err.println("API: Error en login: " + e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "status", "error",
                    "message", "Error interno en el servidor"
                ));
        }
    }
    
    @GetMapping("/usuario/{carnet}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable String carnet) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findByCarnet(carnet);
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener datos del usuario");
        }
    }
}