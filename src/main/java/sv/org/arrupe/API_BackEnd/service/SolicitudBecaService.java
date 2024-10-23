package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.SolicitudBeca;
import sv.org.arrupe.API_BackEnd.repository.SolicitudBecaRepository;
import java.util.List;
import java.util.stream.Collectors;
import sv.org.arrupe.API_BackEnd.dto.SolicitudBecaDTO;

@Service
public class SolicitudBecaService {
    
    @Autowired
    private SolicitudBecaRepository solicitudBecaRepository;

    public List<SolicitudBecaDTO> obtenerSolicitudesPorEstudiante(Long idEstudiante) {
        List<SolicitudBeca> solicitudes = solicitudBecaRepository.findByIdEstudianteOrderByFechaCreacionDesc(idEstudiante);
        
        return solicitudes.stream().map(solicitud -> {
            SolicitudBecaDTO dto = new SolicitudBecaDTO();
            dto.setIdSolicitudBeca(solicitud.getIdSolicitudBeca());
            dto.setIdEstudiante(solicitud.getIdEstudiante());
            // Convertir Integer a Long para idTipoBeca
            dto.setIdTipoBeca(solicitud.getIdTipoBeca() != null ? solicitud.getIdTipoBeca().longValue() : null);
            dto.setNombreTipoBeca(solicitud.getTipoBeca() != null ? solicitud.getTipoBeca().getNombre() : null);
            dto.setFechaCreacion(solicitud.getFechaCreacion());
            dto.setMotivo(solicitud.getMotivo());
            dto.setEstadoSolicitud(solicitud.getEstadoSolicitud());
            return dto;
        }).collect(Collectors.toList());
    }
}