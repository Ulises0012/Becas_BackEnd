package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "beca")
public class Beca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_beca;

    @ManyToOne
    @JoinColumn(name = "Id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "Id_tipo_beca")
    private TipoBeca tipoBeca;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "Motivo")
    private String motivo;

    @Column(name = "estado_beca")
    private String estadoBeca;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (estadoBeca == null) {
            estadoBeca = "Pendiente";
        }
    }
}