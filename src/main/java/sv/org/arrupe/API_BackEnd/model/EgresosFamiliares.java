package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "egresos_familiares")
public class EgresosFamiliares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_egreso")
    private Integer idEgreso;

    @Column(name = "Id_estudiante")
    private Integer idEstudiante;

    @Column(name = "Alimentacion")
    private Double alimentacion;

    @Column(name = "Transporte")
    private Double transporte;

    @Column(name = "Servicios_basicos")
    private Double serviciosBasicos;

    @Column(name = "Pago_vivienda")
    private Double pagoVivienda;

    @Column(name = "Cotizacion")
    private Double cotizacion;

    @Column(name = "Educacion")
    private Double educacion;
}
