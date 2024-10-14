package sv.org.arrupe.API_BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.EstudioSocioeconomico;
import sv.org.arrupe.API_BackEnd.service.EstudioSocioeconomicoService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/estudiosocioeconomico")
public class EstudioSocioeconomicoController {
    @Autowired
    private EstudioSocioeconomicoService estudioSocioeconomicoService;

    @GetMapping
    public List<EstudioSocioeconomico> getAllEstudiosSocioeconomicos() {
        return estudioSocioeconomicoService.getAllEstudiosSocioeconomicos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstudioSocioeconomico(@PathVariable Integer id) {
        estudioSocioeconomicoService.eliminarEstudioSocioeconomico(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudioSocioeconomico> verEstudioSocioeconomico(@PathVariable Integer id) {
        EstudioSocioeconomico estudio = estudioSocioeconomicoService.verEstudioSocioeconomico(id);
        if (estudio != null) {
            return ResponseEntity.ok(estudio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
