package sv.org.arrupe.API_BackEnd.dto;

import lombok.Data;

@Data
public class SolicitudDispositivoDTO {
    private Integer idDispositivo;
    private Integer idTipoDispositivo;
    private Integer cantidad;
}