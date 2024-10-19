package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.org.arrupe.API_BackEnd.model.TipoDispositivo;

@Repository
public interface TipoDispositivoRepository extends JpaRepository<TipoDispositivo, Integer> {
}