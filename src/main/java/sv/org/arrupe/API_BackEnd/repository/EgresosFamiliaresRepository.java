package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.org.arrupe.API_BackEnd.model.EgresosFamiliares;

import java.util.Optional;

@Repository
public interface EgresosFamiliaresRepository extends JpaRepository<EgresosFamiliares, Integer> {
    Optional<EgresosFamiliares> findByIdEstudiante(Long idEstudiante);
}