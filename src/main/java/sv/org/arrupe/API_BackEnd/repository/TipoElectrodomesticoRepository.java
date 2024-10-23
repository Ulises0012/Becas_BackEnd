package sv.org.arrupe.API_BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.arrupe.API_BackEnd.model.TipoElectrodomestico;

public interface TipoElectrodomesticoRepository extends JpaRepository<TipoElectrodomestico, Integer> {
}