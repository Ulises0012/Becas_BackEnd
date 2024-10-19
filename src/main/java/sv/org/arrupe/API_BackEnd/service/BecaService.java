package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.dto.SolicitudBecaDTO;
import sv.org.arrupe.API_BackEnd.model.Beca;
import sv.org.arrupe.API_BackEnd.model.Estudiante;
import sv.org.arrupe.API_BackEnd.model.TipoBeca;
import sv.org.arrupe.API_BackEnd.repository.BecaRepository;
import sv.org.arrupe.API_BackEnd.repository.EstudianteRepository;
import sv.org.arrupe.API_BackEnd.repository.TipoBecaRepository;

@Service
public class BecaService {
    @Autowired
    private BecaRepository becaRepository;
    
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    @Autowired
    private TipoBecaRepository tipoBecaRepository;

    public Beca crearSolicitudBeca(SolicitudBecaDTO solicitudDTO) {
        // Buscar estudiante y tipo de beca
        Estudiante estudiante = estudianteRepository.findById(solicitudDTO.getIdEstudiante())
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        
        TipoBeca tipoBeca = tipoBecaRepository.findById(solicitudDTO.getIdTipoBeca())
            .orElseThrow(() -> new RuntimeException("Tipo de beca no encontrado"));

        // Crear nueva beca
        Beca beca = new Beca();
        beca.setEstudiante(estudiante);
        beca.setTipoBeca(tipoBeca);
        beca.setMotivo(solicitudDTO.getMotivo());
        // La fecha de creación y el estado se establecen automáticamente por @PrePersist

        return becaRepository.save(beca);
    }
}