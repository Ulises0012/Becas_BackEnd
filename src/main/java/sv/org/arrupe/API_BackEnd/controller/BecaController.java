package sv.org.arrupe.API_BackEnd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.org.arrupe.API_BackEnd.model.*;
import sv.org.arrupe.API_BackEnd.service.*;
import sv.org.arrupe.API_BackEnd.dto.SolicitudBecaDTO;
import sv.org.arrupe.API_BackEnd.dto.SolicitudElectrodomesticoDTO;
import sv.org.arrupe.API_BackEnd.dto.SolicitudDispositivoDTO;
import sv.org.arrupe.API_BackEnd.service.EgresosFamiliaresService;
import sv.org.arrupe.API_BackEnd.model.EgresosFamiliares;
import sv.org.arrupe.API_BackEnd.model.IngresosFamiliares;
import sv.org.arrupe.API_BackEnd.service.IngresosFamiliaresService;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import sv.org.arrupe.API_BackEnd.dto.ServiciosFamiliaresDTO;
import sv.org.arrupe.API_BackEnd.dto.ViviendaDTO;

@RestController
@RequestMapping("/api/becados")
@CrossOrigin(origins = "*")
public class BecaController {
    private final UsuarioService usuarioService;
    private final TipoBecaService tipoBecaService;
    private final BecaService becaService;
    private final DispositivoElectronicoService dispositivoService;
    private final ElectrodomesticoService electrodomesticoService;
    private final SolicitudBecaService solicitudBecaService;
    private final ServiciosFamiliaresService serviciosFamiliaresService;

    @Autowired
    public BecaController(
            UsuarioService usuarioService,
            TipoBecaService tipoBecaService,
            BecaService becaService,
            DispositivoElectronicoService dispositivoService,
            ElectrodomesticoService electrodomesticoService,
            SolicitudBecaService solicitudBecaService,
            ServiciosFamiliaresService serviciosFamiliaresService) {
        this.usuarioService = usuarioService;
        this.tipoBecaService = tipoBecaService;
        this.becaService = becaService;
        this.dispositivoService = dispositivoService;
        this.electrodomesticoService = electrodomesticoService;
        this.solicitudBecaService = solicitudBecaService;
        this.serviciosFamiliaresService = serviciosFamiliaresService;
    }
     @Autowired
    private ViviendaService viviendaService;

    @Autowired
    private EgresosFamiliaresService egresosFamiliaresService;

    @Autowired
    private IngresosFamiliaresService ingresosFamiliaresService;

    // Obtener usuario autenticado
    @GetMapping("/usuario-actual")
    public ResponseEntity<Usuario> obtenerUsuarioActual() {
        Usuario usuario = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado."));
        return ResponseEntity.ok(usuario);
    }

    // Obtener tipos de beca
    @GetMapping("/tipos-beca")
    public ResponseEntity<List<TipoBeca>> obtenerTiposDeBeca() {
        List<TipoBeca> tiposDeBeca = tipoBecaService.obtenerTiposDeBeca();
        return ResponseEntity.ok(tiposDeBeca);
    }

    // Solicitar beca
    @PostMapping("/solicitar")
    public ResponseEntity<?> solicitarBeca(@RequestBody SolicitudBecaDTO solicitudDTO) {
        try {
            Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                    .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));

            Beca nuevaBeca = becaService.crearSolicitudBeca(solicitudDTO, usuarioActual);
            return ResponseEntity.ok(nuevaBeca);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    // Nuevo endpoint para obtener mis solicitudes
    @GetMapping("/mis-solicitudes")
    public ResponseEntity<List<SolicitudBecaDTO>> obtenerMisSolicitudes() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        List<SolicitudBecaDTO> solicitudes = solicitudBecaService
                .obtenerSolicitudesPorEstudiante(usuarioActual.getEstudiante().getId_estudiante());
        return ResponseEntity.ok(solicitudes);
    }

    // Endpoints de Dispositivos Electrónicos
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

    @PutMapping("/dispositivos/actualizar")
    public ResponseEntity<?> actualizarDispositivos(@RequestBody List<SolicitudDispositivoDTO> dispositivos) {
        try {
            Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                    .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
            
            List<DispositivoElectronico> dispositivosActualizados = new ArrayList<>();
            List<String> errores = new ArrayList<>();

            for (SolicitudDispositivoDTO dto : dispositivos) {
                try {
                    DispositivoElectronico dispositivoExistente = dispositivoService
                            .getDispositivoById(dto.getIdDispositivo())
                            .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado: " + dto.getIdDispositivo()));

                    if (!dispositivoExistente.getIdEstudiante().equals(
                            usuarioActual.getEstudiante().getId_estudiante())) {
                        errores.add("No autorizado para modificar el dispositivo: " + dto.getIdDispositivo());
                        continue;
                    }

                    dispositivoExistente.setCantidad(dto.getCantidad());
                    DispositivoElectronico actualizado = dispositivoService.updateDispositivo(dispositivoExistente);
                    dispositivosActualizados.add(actualizado);

                } catch (Exception e) {
                    errores.add("Error al actualizar dispositivo " + dto.getIdDispositivo() + ": " + e.getMessage());
                }
            }

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("dispositivosActualizados", dispositivosActualizados);
            respuesta.put("errores", errores);

            if (errores.isEmpty()) {
                return ResponseEntity.ok(respuesta);
            } else if (!dispositivosActualizados.isEmpty()) {
                return ResponseEntity.status(207).body(respuesta);
            } else {
                return ResponseEntity.badRequest().body(respuesta);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error general: " + e.getMessage());
        }
    }

    // Endpoints de Electrodomésticos
    @GetMapping("/electrodomesticos/tipos")
    public ResponseEntity<List<TipoElectrodomestico>> getAllTiposElectrodomestico() {
        return ResponseEntity.ok(electrodomesticoService.getAllTiposElectrodomestico());
    }

    @GetMapping("/electrodomesticos/actuales")
    public ResponseEntity<List<Electrodomestico>> getElectrodomesticosUsuarioActual() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));

        return ResponseEntity.ok(electrodomesticoService
                .getElectrodomesticosByEstudiante(usuarioActual.getEstudiante().getId_estudiante()));
    }

    @PostMapping("/electrodomesticos/agregar")
    public ResponseEntity<Electrodomestico> agregarElectrodomestico(
            @RequestBody Electrodomestico electrodomestico) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));

        electrodomestico.setIdEstudiante(usuarioActual.getEstudiante().getId_estudiante());
        return ResponseEntity.ok(electrodomesticoService.saveElectrodomestico(electrodomestico));
    }

    @PutMapping("/electrodomesticos/actualizar")
    public ResponseEntity<?> actualizarElectrodomesticos(@RequestBody List<SolicitudElectrodomesticoDTO> electrodomesticos) {
        try {
            Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                    .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
            
            List<Electrodomestico> electrodomesticosActualizados = new ArrayList<>();
            List<String> errores = new ArrayList<>();

            for (SolicitudElectrodomesticoDTO dto : electrodomesticos) {
                try {
                    Electrodomestico electrodomesticoExistente = electrodomesticoService
                            .getElectrodomesticoById(dto.getIdElectrodomestico())
                            .orElseThrow(() -> new RuntimeException("Electrodoméstico no encontrado: " + dto.getIdElectrodomestico()));

                    if (!electrodomesticoExistente.getIdEstudiante().equals(
                            usuarioActual.getEstudiante().getId_estudiante())) {
                        errores.add("No autorizado para modificar el electrodoméstico: " + dto.getIdElectrodomestico());
                        continue;
                    }

                    Electrodomestico actualizado = electrodomesticoService.updateElectrodomestico(electrodomesticoExistente);
                    electrodomesticosActualizados.add(actualizado);

                } catch (Exception e) {
                    errores.add("Error al actualizar electrodoméstico " + dto.getIdElectrodomestico() + ": " + e.getMessage());
                }
            }

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("electrodomesticosActualizados", electrodomesticosActualizados);
            respuesta.put("errores", errores);

            if (errores.isEmpty()) {
                return ResponseEntity.ok(respuesta);
            } else if (!electrodomesticosActualizados.isEmpty()) {
                return ResponseEntity.status(207).body(respuesta);
            } else {
                return ResponseEntity.badRequest().body(respuesta);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error general: " + e.getMessage());
        }
    }

    // Endpoints de Egresos Familiares
    @GetMapping("/egresos")
    public ResponseEntity<EgresosFamiliares> obtenerEgresosPropios() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        EgresosFamiliares egresos = egresosFamiliaresService
                .obtenerEgresosPorEstudiante(usuarioActual.getEstudiante().getId_estudiante());
        return ResponseEntity.ok(egresos);
    }

    @PutMapping("/egresos")
    public ResponseEntity<EgresosFamiliares> actualizarEgresosPropios(@RequestBody EgresosFamiliares egresosFamiliares) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        EgresosFamiliares egresosActualizados = egresosFamiliaresService
                .actualizarEgresos(usuarioActual.getEstudiante().getId_estudiante(), egresosFamiliares);
        return ResponseEntity.ok(egresosActualizados);
    }

    // Endpoints de Ingresos Familiares
    @GetMapping("/ingresos")
    public ResponseEntity<IngresosFamiliares> obtenerIngresosPropios() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        return ingresosFamiliaresService.getIngresosByEstudiante(usuarioActual.getEstudiante().getId_estudiante())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ingresos")
    public ResponseEntity<IngresosFamiliares> actualizarIngresosPropios(@RequestBody IngresosFamiliares ingresosFamiliares) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        IngresosFamiliares ingresosActualizados = ingresosFamiliaresService
                .updateIngresosFamiliares(usuarioActual.getEstudiante().getId_estudiante(), ingresosFamiliares);
        return ResponseEntity.ok(ingresosActualizados);
    }
    
     @GetMapping("/servicios")
    public ResponseEntity<ServiciosFamiliares> obtenerServiciosPropios() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        ServiciosFamiliares servicios = serviciosFamiliaresService
                .obtenerPorIdEstudiante(usuarioActual.getEstudiante().getId_estudiante());
        return ResponseEntity.ok(servicios);
    }

    @PutMapping("/servicios")
    public ResponseEntity<ServiciosFamiliares> actualizarServiciosPropios(
            @RequestBody ServiciosFamiliaresDTO serviciosDTO) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        ServiciosFamiliares serviciosActualizados = serviciosFamiliaresService
                .actualizarServiciosFamiliares(usuarioActual.getEstudiante().getId_estudiante(), serviciosDTO);
        return ResponseEntity.ok(serviciosActualizados);
    }
    
        @GetMapping("/vivienda")
    public ResponseEntity<ViviendaDTO> obtenerViviendaPropia() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        ViviendaDTO vivienda = viviendaService.obtenerViviendaPorEstudiante(usuarioActual.getEstudiante().getId_estudiante());
        if (vivienda != null) {
            return ResponseEntity.ok(vivienda);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/vivienda")
    public ResponseEntity<ViviendaDTO> actualizarViviendaPropia(@RequestBody ViviendaDTO viviendaDTO) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual()
                .orElseThrow(() -> new RuntimeException("No se encontró al usuario autenticado"));
        
        ViviendaDTO viviendaActualizada = viviendaService.guardarVivienda(viviendaDTO);
        return ResponseEntity.ok(viviendaActualizada);
    }


}