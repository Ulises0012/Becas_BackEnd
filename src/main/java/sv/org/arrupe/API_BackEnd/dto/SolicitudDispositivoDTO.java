package sv.org.arrupe.API_BackEnd.dto;

public class SolicitudDispositivoDTO {
    private Long idDispositivo;  // ID del dispositivo
    private Integer cantidad;     // Cantidad solicitada

    // Constructor vacío
    public SolicitudDispositivoDTO() {}

    // Constructor con parámetros
    public SolicitudDispositivoDTO(Long idDispositivo, Integer cantidad) {
        this.idDispositivo = idDispositivo;
        this.cantidad = cantidad;
    }

    // Getter para idDispositivo
    public Long getIdDispositivo() {
        return idDispositivo;  // Devuelve el ID del dispositivo
    }

    // Setter para idDispositivo
    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;  // Asigna el ID del dispositivo
    }

    // Getter para cantidad
    public Integer getCantidad() {
        return cantidad;  // Devuelve la cantidad solicitada
    }

    // Setter para cantidad
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;  // Asigna la cantidad solicitada
    }

    @Override
    public String toString() {
        return "SolicitudDispositivoDTO{" +
                "idDispositivo=" + idDispositivo +
                ", cantidad=" + cantidad +
                '}';
    }
}
