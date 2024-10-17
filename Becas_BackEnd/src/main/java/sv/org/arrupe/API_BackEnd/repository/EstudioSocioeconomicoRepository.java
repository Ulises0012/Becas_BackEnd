package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.org.arrupe.API_BackEnd.model.EstudioSocioeconomico;

import java.util.List;

@Repository
public interface EstudioSocioeconomicoRepository extends JpaRepository<EstudioSocioeconomico, Integer> {
    List<EstudioSocioeconomico> findAllByOrderByIdEstudianteAsc();
}
