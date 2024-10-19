package sv.org.arrupe.API_BackEnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.TipoBeca;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.service.TipoBecaService;
import sv.org.arrupe.API_BackEnd.service.UsuarioService;

import java.util.List;
import sv.org.arrupe.API_BackEnd.dto.SolicitudBecaDTO;
import sv.org.arrupe.API_BackEnd.model.Beca;
import sv.org.arrupe.API_BackEnd.service.BecaService;

@RestController
@RequestMapping("/api/becados")
public class BecaController {

   private final UsuarioService usuarioService;
    private final TipoBecaService tipoBecaService;
    private final BecaService becaService;

    public BecaController(UsuarioService usuarioService, TipoBecaService tipoBecaService, BecaService becaService) {
        this.usuarioService = usuarioService;
        this.tipoBecaService = tipoBecaService;
        this.becaService = becaService;
    }

    // Endpoint para obtener al usuario actualmente autenticado
    @GetMapping("/usuario-actual")
    public ResponseEntity<Usuario> obtenerUsuarioActual() {
        Usuario usuario = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado."));
        return ResponseEntity.ok(usuario);
    }

    // Endpoint para pruebas: Devuelve un saludo con el nombre del usuario logueado
    @GetMapping("/saludo")
    public ResponseEntity<String> saludo() {
        Usuario usuario = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado."));
        return ResponseEntity.ok("Hola, " + usuario.getEstudiante().getNombre() + usuario.getEstudiante().getApellido() + "!");
    }

    // NUEVO: Endpoint para obtener los tipos de beca
    @GetMapping("/tipos-beca")
    public ResponseEntity<List<TipoBeca>> obtenerTiposDeBeca() {
        List<TipoBeca> tiposDeBeca = tipoBecaService.obtenerTiposDeBeca();
        return ResponseEntity.ok(tiposDeBeca);
    }
    @PostMapping("/solicitar")
public ResponseEntity<Beca> solicitarBeca(@RequestBody SolicitudBecaDTO solicitudDTO) {
    // Obtiene el usuario actualmente autenticado
    Usuario usuario = usuarioService.obtenerUsuarioActual()
            .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado."));

    // Crea la nueva beca y asocia al estudiante logueado
    Beca nuevaBeca = new Beca();
    nuevaBeca.setMotivo(solicitudDTO.getMotivo());
    nuevaBeca.setTipoBeca(tipoBecaService.obtenerTipoBecaPorId(solicitudDTO.getIdTipoBeca())); // Asumiendo que tienes este método en tu servicio
    nuevaBeca.setEstudiante(usuario.getEstudiante()); // Asocia el estudiante logueado

    // Crea la beca a través del servicio
    Beca becaCreada = becaService.crearBeca(nuevaBeca);
    return ResponseEntity.ok(becaCreada);
}
}
