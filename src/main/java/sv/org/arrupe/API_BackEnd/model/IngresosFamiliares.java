package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ingresos_familiares")
public class IngresosFamiliares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_ingreso")
    private Integer idIngreso;
    
    @Column(name = "Id_estudiante")
    private Integer idEstudiante;
    
    @Column(name = "Ingreso_total")
    private Double ingresoTotal;
    
    @Column(name = "Negocio_propio")
    private Boolean negocioPropio;
    
    @Column(name = "Direccion_negocio")
    private String direccionNegocio;
    
    @Column(name = "Ingreso_per_capita")
    private Double ingresoPerCapita;
}