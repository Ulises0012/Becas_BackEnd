package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "beca")
public class Becado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_beca") // Ajuste para coincidir con el nombre exacto de la columna
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "Id_tipo_beca", nullable = false)
    private TipoBeca tipoBeca;

    @Column(name = "Fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "Motivo")
    private String motivo;

    @Column(name = "Estado_beca")
    private String estadoBeca;

    // Constructores, getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(TipoBeca tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstadoBeca() {
        return estadoBeca;
    }

    public void setEstadoBeca(String estadoBeca) {
        this.estadoBeca = estadoBeca;
    }
}