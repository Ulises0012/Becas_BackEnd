package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "electrodomesticos")
public class Electrodomestico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_electrodomestico")
    private Long idElectrodomestico;

    @Column(name = "Id_estudiante")
    private Long idEstudiante;

    @ManyToOne
    @JoinColumn(name = "id_tipo_electrodomestico", referencedColumnName = "id_tipo")  // Cambiado el nombre de la columna
    private TipoElectrodomestico tipoElectrodomestico;

    @Column(name = "Cantidad")
    private Integer cantidad;
}
