package sv.org.arrupe.API_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(unique = true, nullable = false)
    private String carnet;

    @Column(nullable = false)
    private String password;

    @Convert(converter = RolConverter.class)
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    // Getters y setters explícitos
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    // Enum para los roles
    public enum Rol {
        ESTUDIANTE, ADMIN;

        public String toUpperCase() {
            return this.name();
        }

        public String toLowerCase() {
            return this.name().toLowerCase();
        }
    }
}

// Conversor personalizado para el Enum Rol
@Converter(autoApply = true)
class RolConverter implements AttributeConverter<Usuario.Rol, String> {
    @Override
    public String convertToDatabaseColumn(Usuario.Rol rol) {
        if (rol == null) {
            return null;
        }
        return rol.name();
    }

    @Override
    public Usuario.Rol convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            // Convierte ignorando mayúsculas/minúsculas
            return Usuario.Rol.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol no válido: " + dbData);
        }
    }
}