package sv.org.arrupe.API_BackEnd.dto;

public class SolicitudDispositivoDTO {
    private Long idDispositivo;
    private Integer idTipoDispositivo;
    private Integer cantidad;

    // Constructor vacío
    public SolicitudDispositivoDTO() {}

    // Constructor con parámetros
    public SolicitudDispositivoDTO(Long idDispositivo, Integer idTipoDispositivo, Integer cantidad) {
        this.idDispositivo = idDispositivo;
        this.idTipoDispositivo = idTipoDispositivo;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Integer getIdTipoDispositivo() {
        return idTipoDispositivo;
    }

    public void setIdTipoDispositivo(Integer idTipoDispositivo) {
        this.idTipoDispositivo = idTipoDispositivo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "SolicitudDispositivoDTO{" +
                "idDispositivo=" + idDispositivo +
                ", idTipoDispositivo=" + idTipoDispositivo +
                ", cantidad=" + cantidad +
                '}';
    }
}