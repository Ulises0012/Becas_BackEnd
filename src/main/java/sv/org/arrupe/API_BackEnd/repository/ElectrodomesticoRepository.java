package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.arrupe.API_BackEnd.model.Electrodomestico;
import java.util.List;

public interface ElectrodomesticoRepository extends JpaRepository<Electrodomestico, Long> {  // Cambiado a Long
    List<Electrodomestico> findByIdEstudiante(Long idEstudiante);  // Cambiado a Long
}
