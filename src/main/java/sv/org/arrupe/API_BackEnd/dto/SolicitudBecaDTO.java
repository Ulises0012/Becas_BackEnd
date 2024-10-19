package sv.org.arrupe.API_BackEnd.dto;

public class SolicitudBecaDTO {
    private Long idTipoBeca;
    private String motivo;
    
    public Long getIdTipoBeca() {
        return idTipoBeca;
    }
    
    public void setIdTipoBeca(Long idTipoBeca) {
        this.idTipoBeca = idTipoBeca;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}