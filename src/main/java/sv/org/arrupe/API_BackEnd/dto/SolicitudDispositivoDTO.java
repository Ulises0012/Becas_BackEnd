package sv.org.arrupe.API_BackEnd.dto;

import lombok.Data;

@Data
public class SolicitudDispositivoDTO {
    private Long idDispositivo;
    private Integer idTipoDispositivo;
    private Integer cantidad;
}