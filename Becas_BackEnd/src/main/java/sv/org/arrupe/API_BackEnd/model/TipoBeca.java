package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;

@Entity
public class TipoBeca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beca")  // Especificar el nombre de la columna
    private Long idBeca;

    @Column(name = "nombre_beca") // Especificar el nombre de la columna
    private String nombre;
    public TipoBeca() {}

    public TipoBeca(String nombre, String descripcion) {
        this.nombre = nombre;
    }

    public Long getIdBeca() {
        return idBeca;  // Cambiado para mayor claridad
    }

    public void setIdBeca(Long idBeca) {
        this.idBeca = idBeca;  // Cambiado para mayor claridad
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
