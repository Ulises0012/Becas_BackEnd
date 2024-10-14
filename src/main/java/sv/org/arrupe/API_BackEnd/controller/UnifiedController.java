package sv.org.arrupe.API_BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.UsuarioRepository;
import sv.org.arrupe.API_BackEnd.service.UsuarioService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UnifiedController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> procesarLogin(@RequestParam String carnet, @RequestParam String password) {
        Optional<Usuario> usuario = usuarioService.autenticar(carnet, password);

        if (usuario.isPresent()) {
            Usuario.Rol rol = usuario.get().getRol();
            return ResponseEntity.ok()
                    .body(Map.of(
                            "status", "success",
                            "message", "Login exitoso",
                            "carnet", carnet,
                            "rol", rol
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "status", "error",
                            "message", "Credenciales inválidas"
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
