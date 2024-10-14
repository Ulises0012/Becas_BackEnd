package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "estudiosocioeconomico")
public class EstudioSocioeconomico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudio")
    private Integer idEstudio;

    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @Column(name = "id_vivienda")
    private Integer idVivienda;

    @Column(name = "id_ingresos")
    private Integer idIngresos;

    @Column(name = "id_egresos")
    private Integer idEgresos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_beca")
    private TipoBeca tipoBeca;

    @Column(name = "ingresos_familiares_totales")
    private BigDecimal ingresosFamiliaresTotales;

    @Column(name = "ingreso_per_capita")
    private BigDecimal ingresoPerCapita;

    // Getters y Setters

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Integer idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Integer getIdIngresos() {
        return idIngresos;
    }

    public void setIdIngresos(Integer idIngresos) {
        this.idIngresos = idIngresos;
    }

    public Integer getIdEgresos() {
        return idEgresos;
    }

    public void setIdEgresos(Integer idEgresos) {
        this.idEgresos = idEgresos;
    }

    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(TipoBeca tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public BigDecimal getIngresosFamiliaresTotales() {
        return ingresosFamiliaresTotales;
    }

    public void setIngresosFamiliaresTotales(BigDecimal ingresosFamiliaresTotales) {
        this.ingresosFamiliaresTotales = ingresosFamiliaresTotales;
    }

    public BigDecimal getIngresoPerCapita() {
        return ingresoPerCapita;
    }

    public void setIngresoPerCapita(BigDecimal ingresoPerCapita) {
        this.ingresoPerCapita = ingresoPerCapita;
    }

    public enum TipoBeca {
        BECA_COMPLETA,
        BECA_LIBRO,
        BECA_UNIFORME,
        BECA_ALIMENTO
    }
}
