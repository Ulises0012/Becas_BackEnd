package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_beca")
public class TipoBeca {

    @Id
    @Column(name = "id_beca")  // Ajustar el nombre de la columna
    private Long id;

    @Column(name = "nombre_beca")  // Ajustar el nombre de la columna
    private String nombre;

    // Constructor vacío
    public TipoBeca() {}

    // Constructor con parámetros
    public TipoBeca(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
