/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author adria
 */
@Entity
@Table(name = "estudiosocioeconomico")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @Column(name = "tipo_beca")
    @Enumerated(EnumType.STRING)
    private TipoBeca tipoBeca;

    @Column(name = "ingresos_familiares_totales")
    private BigDecimal ingresosFamiliaresTotales;

    @Column(name = "ingreso_per_capita")
    private BigDecimal ingresoPerCapita;

    // Getters y setters
}
