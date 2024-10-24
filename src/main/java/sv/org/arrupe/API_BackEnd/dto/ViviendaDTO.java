package sv.org.arrupe.API_BackEnd.dto;

public class ViviendaDTO {
    private Integer idVivienda;
    private Long idEstudiante;  // Cambiado a Long
    private Integer idTipoVivienda;
    private Integer numeroPersonas;
    private Integer numeroHabitaciones;
    private Integer vehiculos;

    // Constructor
    public ViviendaDTO() {}

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