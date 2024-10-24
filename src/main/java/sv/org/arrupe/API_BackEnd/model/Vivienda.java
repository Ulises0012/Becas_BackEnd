package sv.org.arrupe.API_BackEnd.model;
import jakarta.persistence.*;

@Entity
@Table(name = "vivienda")
public class Vivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vivienda")
    private Integer idVivienda;
    
    @Column(name = "id_estudiante")
    private Long idEstudiante;  // Cambiado a Long
    
    @Column(name = "id_tipo_vivienda")
    private Integer idTipoVivienda;
    
    @Column(name = "numero_personas")
    private Integer numeroPersonas;
    
    @Column(name = "numero_habitaciones")
    private Integer numeroHabitaciones;
    
    @Column(name = "vehiculos")
    private Integer vehiculos;

    // Constructor
    public Vivienda() {}

    // Getters y Setters
    public Integer getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Integer idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Long getIdEstudiante() {  // Cambiado a Long
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {  // Cambiado a Long
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(Integer idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Integer getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Integer getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Integer vehiculos) {
        this.vehiculos = vehiculos;
    }
}
