package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.org.arrupe.API_BackEnd.model.DispositivoElectronico;
import java.util.List;

@Repository
public interface DispositivoElectronicoRepository extends JpaRepository<DispositivoElectronico, Long> {
    List<DispositivoElectronico> findByIdEstudiante(Long idEstudiante);
}