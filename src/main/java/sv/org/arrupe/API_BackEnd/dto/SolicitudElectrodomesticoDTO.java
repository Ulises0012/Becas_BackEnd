package sv.org.arrupe.API_BackEnd.dto;

import lombok.Data;

@Data
public class SolicitudElectrodomesticoDTO {
    private Long idElectrodomestico;   // Usamos Long en lugar de Integer para evitar errores de tipo
    private Integer idTipoElectrodomestico;
    private Integer cantidad;
}
