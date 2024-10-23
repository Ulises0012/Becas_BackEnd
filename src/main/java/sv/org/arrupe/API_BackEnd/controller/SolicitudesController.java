package sv.org.arrupe.API_BackEnd.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.Becado;
import sv.org.arrupe.API_BackEnd.service.BecadoService;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "http://localhost:8080")
public class SolicitudesController {

    @Autowired
    private BecadoService becadoService;

    @GetMapping
    public List<BecadoResponse> getAllBecados() {
        return becadoService.getAllBecados()
                .stream()
                .filter(becado -> "Pendiente".equals(becado.getEstadoBeca())) // Filtrar solo los pendientes
                .map(becado -> new BecadoResponse(
                        becado.getId(),
                        becado.getEstudiante().getNombre(),
                        becado.getEstudiante().getApellido(),
                        becado.getTipoBeca().getNombre(),
                        becado.getMotivo()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BecadoResponse> getBecadoById(@PathVariable Long id) {
        Becado becado = becadoService.getBecadoById(id);
        BecadoResponse response = new BecadoResponse(
                becado.getId(),
                becado.getEstudiante().getNombre(),
                becado.getEstudiante().getApellido(),
                becado.getTipoBeca().getNombre(),
                becado.getMotivo() // Incluimos el motivo
        );
        return ResponseEntity.ok(response);
    }

    // Método para aprobar la beca
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Becado> aprobarBeca(@PathVariable Long id) {
        // Llama al servicio para aprobar el becado (cambiar el estado a 'Activo')
        Becado becadoAprobado = becadoService.aprobarBeca(id);
        return ResponseEntity.ok(becadoAprobado);
    }

    // Método para rechazar la beca
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Becado> rechazarBeca(@PathVariable Long id) {
        // Llama al servicio para rechazar el becado (cambiar el estado a 'Rechazada')
        Becado becadoRechazado = becadoService.rechazarBeca(id);
        return ResponseEntity.ok(becadoRechazado);
    }

    @GetMapping("/search")
    public List<Becado> searchBecados(@RequestParam String query) {
        return becadoService.searchBecados(query);
    }

    // Clase interna para la respuesta
    public static class BecadoResponse {

        private Long id;
        private String nombreEstudiante;
        private String apellidoEstudiante;
        private String nombreBeca;
        private String motivoBeca;

        public BecadoResponse(Long id, String nombreEstudiante, String apellidoEstudiante, String nombreBeca, String motivoBeca) {
            this.id = id;
            this.nombreEstudiante = nombreEstudiante;
            this.apellidoEstudiante = apellidoEstudiante;
            this.nombreBeca = nombreBeca;
            this.motivoBeca = motivoBeca;
        }

        // Getters
        public Long getId() {
            return id;
        }

        public String getNombreEstudiante() {
            return nombreEstudiante;
        }

        public String getApellidoEstudiante() {
            return apellidoEstudiante;
        }

        public String getNombreBeca() {
            return nombreBeca;
        }

        public String getMotivoBeca() {
            return motivoBeca;
        }
    }
}
