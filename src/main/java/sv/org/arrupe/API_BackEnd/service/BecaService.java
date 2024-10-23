package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.dto.SolicitudBecaDTO;
import sv.org.arrupe.API_BackEnd.model.Beca;
import sv.org.arrupe.API_BackEnd.model.Estudiante;
import sv.org.arrupe.API_BackEnd.model.TipoBeca;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.BecaRepository;
import sv.org.arrupe.API_BackEnd.repository.TipoBecaRepository;

@Service
public class BecaService {
    @Autowired
    private BecaRepository becaRepository;
    
    @Autowired
    private TipoBecaRepository tipoBecaRepository;
    
    public Beca crearSolicitudBeca(SolicitudBecaDTO solicitudDTO, Usuario usuarioActual) {
        if (usuarioActual == null || usuarioActual.getEstudiante() == null) {
            throw new IllegalArgumentException("No hay un estudiante autenticado");
        }
        
        if (solicitudDTO.getIdTipoBeca() == null) {
            throw new IllegalArgumentException("El ID del tipo de beca no puede ser nulo");
        }
        
        // Obtener el estudiante del usuario actual
        Estudiante estudiante = usuarioActual.getEstudiante();
        
        TipoBeca tipoBeca = tipoBecaRepository.findById(solicitudDTO.getIdTipoBeca())
            .orElseThrow(() -> new RuntimeException("Tipo de beca no encontrado con ID: " + solicitudDTO.getIdTipoBeca()));
        
        Beca beca = new Beca();
        beca.setEstudiante(estudiante);
        beca.setTipoBeca(tipoBeca);
        beca.setMotivo(solicitudDTO.getMotivo());
        
        return becaRepository.save(beca);
    }
}