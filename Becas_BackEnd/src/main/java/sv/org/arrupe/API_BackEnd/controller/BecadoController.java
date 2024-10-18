package sv.org.arrupe.API_BackEnd.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.Becado;
import sv.org.arrupe.API_BackEnd.service.BecadoService;

@RestController
@RequestMapping("/api/becados")
@CrossOrigin(origins = "http://localhost:8080")
public class BecadoController {

    @Autowired
    private BecadoService becadoService;

    @GetMapping
    public List<BecadoResponse> getAllBecados() {
        return becadoService.getAllBecados()
            .stream()
            .map(becado -> new BecadoResponse(
                becado.getEstudiante().getNombre(), // Verifica que este método existe
                becado.getEstudiante().getApellido(), // Verifica que este método existe
                becado.getTipoBeca().getNombre() // Asegúrate que tipoBeca tiene getNombre()
            ))
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BecadoResponse> getBecadoById(@PathVariable Long id) {
        Becado becado = becadoService.getBecadoById(id);
        BecadoResponse response = new BecadoResponse(
            becado.getEstudiante().getNombre(), // Verifica que este método existe
            becado.getEstudiante().getApellido(), // Verifica que este método existe
            becado.getTipoBeca().getNombre() // Asegúrate que tipoBeca tiene getNombre()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Becado> createBecado(@RequestBody Becado becado) {
        Becado newBecado = becadoService.createBecado(becado);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBecado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Becado> updateBecado(@PathVariable Long id, @RequestBody Becado becadoDetails) {
        Becado updatedBecado = becadoService.updateBecado(id, becadoDetails);
        return ResponseEntity.ok(updatedBecado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBecado(@PathVariable Long id) {
        becadoService.deleteBecado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Becado> searchBecados(@RequestParam String query) {
        return becadoService.searchBecados(query);
    }

    // Clase interna para la respuesta
    public static class BecadoResponse {
        private String nombreEstudiante;
        private String apellidoEstudiante;
        private String nombreBeca;

        public BecadoResponse(String nombreEstudiante, String apellidoEstudiante, String nombreBeca) {
            this.nombreEstudiante = nombreEstudiante;
            this.apellidoEstudiante = apellidoEstudiante;
            this.nombreBeca = nombreBeca;
        }

        // Getters
        public String getNombreEstudiante() {
            return nombreEstudiante;
        }

        public String getApellidoEstudiante() {
            return apellidoEstudiante;
        }

        public String getNombreBeca() {
            return nombreBeca;
        }
    }
}
