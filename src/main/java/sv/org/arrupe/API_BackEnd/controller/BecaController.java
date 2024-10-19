package sv.org.arrupe.API_BackEnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.*;
import sv.org.arrupe.API_BackEnd.service.*;
import sv.org.arrupe.API_BackEnd.dto.SolicitudBecaDTO;
import java.util.List;

@RestController
@RequestMapping("/api/becados")
@CrossOrigin(origins = "*")
public class BecaController {
    private final UsuarioService usuarioService;
    private final TipoBecaService tipoBecaService;
    private final BecaService becaService;
    private final DispositivoElectronicoService dispositivoService;

    public BecaController(
            UsuarioService usuarioService, 
            TipoBecaService tipoBecaService, 
            BecaService becaService,
            DispositivoElectronicoService dispositivoService) {
        this.usuarioService = usuarioService;
        this.tipoBecaService = tipoBecaService;
        this.becaService = becaService;
        this.dispositivoService = dispositivoService;
    }

    @GetMapping("/usuario-actual")
    public ResponseEntity<Usuario> obtenerUsuarioActual() {
        Usuario usuario = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado."));
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/saludo")
    public ResponseEntity<String> saludo() {
        Usuario usuario = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado."));
        return ResponseEntity.ok("Hola, " + usuario.getEstudiante().getNombre() + " " + 
                               usuario.getEstudiante().getApellido() + "!");
    }

    @GetMapping("/tipos-beca")
    public ResponseEntity<List<TipoBeca>> obtenerTiposDeBeca() {
        List<TipoBeca> tiposDeBeca = tipoBecaService.obtenerTiposDeBeca();
        return ResponseEntity.ok(tiposDeBeca);
    }

    @PostMapping("/solicitar")
    public ResponseEntity<?> solicitarBeca(@RequestBody SolicitudBecaDTO solicitudDTO) {
        try {
            if (solicitudDTO == null) {
                return ResponseEntity.badRequest()
                    .body("Los datos de la solicitud no pueden ser nulos");
            }
            
            Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
            
            Beca nuevaBeca = becaService.crearSolicitudBeca(solicitudDTO, usuarioActual);
            return ResponseEntity.ok(nuevaBeca);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/dispositivos/tipos")
    public ResponseEntity<List<TipoDispositivo>> getAllTiposDispositivo() {
        return ResponseEntity.ok(dispositivoService.getAllTiposDispositivo());
    }

    @GetMapping("/dispositivos/actuales")
    public ResponseEntity<List<DispositivoElectronico>> getDispositivosUsuarioActual() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        return ResponseEntity.ok(dispositivoService
                .getDispositivosByEstudiante(usuarioActual.getEstudiante().getId_estudiante()));
    }

    @PostMapping("/dispositivos/agregar")
    public ResponseEntity<DispositivoElectronico> agregarDispositivo(
            @RequestBody DispositivoElectronico dispositivo) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        dispositivo.setIdEstudiante(usuarioActual.getEstudiante().getId_estudiante());
        return ResponseEntity.ok(dispositivoService.saveDispositivo(dispositivo));
    }

    @PutMapping("/dispositivos/actualizar/{id}")
    public ResponseEntity<DispositivoElectronico> actualizarDispositivo(
            @PathVariable Long id, 
            @RequestBody DispositivoElectronico dispositivo) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        if (!dispositivo.getIdEstudiante().equals(usuarioActual.getEstudiante().getId_estudiante())) {
            throw new RuntimeException("No autorizado para modificar este dispositivo");
        }
        
        dispositivo.setIdDispositivo(id);
        return ResponseEntity.ok(dispositivoService.updateDispositivo(dispositivo));
    }

    @DeleteMapping("/dispositivos/eliminar/{id}")
    public ResponseEntity<Void> eliminarDispositivo(@PathVariable Long id) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        DispositivoElectronico dispositivo = dispositivoService.getDispositivoById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado"));
        
        if (!dispositivo.getIdEstudiante().equals(usuarioActual.getEstudiante().getId_estudiante())) {
            throw new RuntimeException("No autorizado para eliminar este dispositivo");
        }
        
        dispositivoService.deleteDispositivo(id);
        return ResponseEntity.ok().build();
    }
}