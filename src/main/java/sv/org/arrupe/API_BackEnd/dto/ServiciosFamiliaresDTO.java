package sv.org.arrupe.API_BackEnd.dto;

import java.math.BigDecimal;

public class ServiciosFamiliaresDTO {
    private Long idServicio;
    private Long idEstudiante;
    private BigDecimal luz;
    private BigDecimal agua;
    private BigDecimal celular;
    private BigDecimal datosMoviles;
    private BigDecimal cableEInternet;

    // Constructor por defecto
    public ServiciosFamiliaresDTO() {
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
