package sv.org.arrupe.API_BackEnd.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.arrupe.API_BackEnd.model.Vivienda;
import java.util.Optional;

public interface ViviendaRepository extends JpaRepository<Vivienda, Integer> {
    Optional<Vivienda> findByIdEstudiante(Long idEstudiante);  // Cambiado a Long
}