package sv.org.arrupe.API_BackEnd.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dispositivos_electronicos")
public class DispositivoElectronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_dispositivo")
    private Long idDispositivo;

    @Column(name = "Id_estudiante")
    private Long idEstudiante;

    @ManyToOne
    @JoinColumn(name = "Id_tipo", referencedColumnName = "id_tipo")
    private TipoDispositivo tipoDispositivo;

    @Column(name = "Cantidad")
    private Integer cantidad;

    public void setIdEstudiante(Long id_estudiante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}