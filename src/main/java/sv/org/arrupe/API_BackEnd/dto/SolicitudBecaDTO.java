package sv.org.arrupe.API_BackEnd.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SolicitudBecaDTO {
    // Campos del DTO original en el paquete dto
    private Long idTipoBeca;
    private String motivo;
    
    // Campos adicionales del DTO que estaba en el paquete model
    private Long idSolicitudBeca;
    private Integer idEstudiante;
    private String nombreTipoBeca;
    private LocalDateTime fechaCreacion;
    private String estadoSolicitud;

    // Constructor vacío
    public SolicitudBecaDTO() {
    }

    // Constructor para crear solicitud nueva (campos mínimos necesarios)
    public SolicitudBecaDTO(Long idTipoBeca, String motivo) {
        this.idTipoBeca = idTipoBeca;
        this.motivo = motivo;
    }

    // Constructor completo
    public SolicitudBecaDTO(Long idSolicitudBeca, Integer idEstudiante, Long idTipoBeca, 
                           String nombreTipoBeca, LocalDateTime fechaCreacion, 
                           String motivo, String estadoSolicitud) {
        this.idSolicitudBeca = idSolicitudBeca;
        this.idEstudiante = idEstudiante;
        this.idTipoBeca = idTipoBeca;
        this.nombreTipoBeca = nombreTipoBeca;
        this.fechaCreacion = fechaCreacion;
        this.motivo = motivo;
        this.estadoSolicitud = estadoSolicitud;
    }
}