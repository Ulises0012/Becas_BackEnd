package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.arrupe.API_BackEnd.model.SolicitudBeca;
import java.util.List;

public interface SolicitudBecaRepository extends JpaRepository<SolicitudBeca, Long> {
    List<SolicitudBeca> findByIdEstudianteOrderByFechaCreacionDesc(Long idEstudiante);  // Cambiado a Long
}