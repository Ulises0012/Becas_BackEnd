package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estudiante;

    @Column(name = "nombres")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;

    // Otros atributos relacionados con Estudiante
}
