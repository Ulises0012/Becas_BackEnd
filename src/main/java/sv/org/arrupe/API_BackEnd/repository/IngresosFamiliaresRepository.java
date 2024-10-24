package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.arrupe.API_BackEnd.model.IngresosFamiliares;
import java.util.Optional;

public interface IngresosFamiliaresRepository extends JpaRepository<IngresosFamiliares, Long> {
    Optional<IngresosFamiliares> findByIdEstudiante(Long idEstudiante);
}