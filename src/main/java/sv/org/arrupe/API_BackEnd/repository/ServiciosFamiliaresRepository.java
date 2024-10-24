package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.org.arrupe.API_BackEnd.model.ServiciosFamiliares;
import java.util.Optional;

@Repository
public interface ServiciosFamiliaresRepository extends JpaRepository<ServiciosFamiliares, Long> {
    Optional<ServiciosFamiliares> findByIdEstudiante(Long idEstudiante);
}