package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicios_familiares")
public class ServiciosFamiliares {

    @Id
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(name = "luz")
    private BigDecimal luz;

    @Column(name = "agua")
    private BigDecimal agua;

    @Column(name = "celular")
    private BigDecimal celular;

    @Column(name = "datos_moviles")
    private BigDecimal datosMoviles;

    @Column(name = "cable_e_internet")
    private BigDecimal cableEInternet;

    // Constructor por defecto
    public ServiciosFamiliares() {
    }

    // Getters y Setters
    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public BigDecimal getLuz() {
        return luz;
    }

    public void setLuz(BigDecimal luz) {
        this.luz = luz;
    }

    public BigDecimal getAgua() {
        return agua;
    }

    public void setAgua(BigDecimal agua) {
        this.agua = agua;
    }

    public BigDecimal getCelular() {
        return celular;
    }

    public void setCelular(BigDecimal celular) {
        this.celular = celular;
    }

    public BigDecimal getDatosMoviles() {
        return datosMoviles;
    }

    public void setDatosMoviles(BigDecimal datosMoviles) {
        this.datosMoviles = datosMoviles;
    }

    public BigDecimal getCableEInternet() {
        return cableEInternet;
    }

    public void setCableEInternet(BigDecimal cableEInternet) {
        this.cableEInternet = cableEInternet;
    }
}
