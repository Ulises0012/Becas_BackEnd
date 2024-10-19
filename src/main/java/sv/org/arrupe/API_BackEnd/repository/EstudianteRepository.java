package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.arrupe.API_BackEnd.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}