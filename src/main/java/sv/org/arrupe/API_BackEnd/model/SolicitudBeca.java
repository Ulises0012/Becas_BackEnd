package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "beca")
@Data
public class SolicitudBeca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beca")
    private Long idSolicitudBeca;
    
    @Column(name = "Id_estudiante")
    private Integer idEstudiante;
    
    @Column(name = "Id_tipo_beca")
    private Integer idTipoBeca;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "Motivo")
    private String motivo;
    
    @Column(name = "estado_beca")
    private String estadoSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_tipo_beca", insertable = false, updatable = false)
    private TipoBeca tipoBeca;
}