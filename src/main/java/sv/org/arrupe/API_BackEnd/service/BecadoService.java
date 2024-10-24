package sv.org.arrupe.API_BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.org.arrupe.API_BackEnd.exception.ResourceNotFoundException;
import sv.org.arrupe.API_BackEnd.model.Becado;
import sv.org.arrupe.API_BackEnd.model.TipoBeca;
import sv.org.arrupe.API_BackEnd.repository.BecadoRepository;
import sv.org.arrupe.API_BackEnd.repository.TipoBecaRepository; // Asegúrate de tener este repositorio

@Service
public class BecadoService {

    @Autowired
    private BecadoRepository becadoRepository;

    @Autowired
    private TipoBecaRepository tipoBecaRepository; // Inyección del repositorio TipoBeca

    public List<Becado> getAllBecados() {
        return becadoRepository.findAll();
    }

    public Becado getBecadoById(Long id) {
        return becadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Becado no encontrado con id: " + id));
    }

    public Becado createBecado(Becado becado) {
        return becadoRepository.save(becado);
    }

    @Transactional
public Becado actualizarSoloTipoBeca(Long idBecado, TipoBeca nuevoTipoBeca) {
    // Guardar TipoBeca si no está persistido
    if (nuevoTipoBeca.getIdBeca() == null) {
        tipoBecaRepository.save(nuevoTipoBeca);
    }

    Becado becado = getBecadoById(idBecado);
    becado.setTipoBeca(nuevoTipoBeca);
    return becadoRepository.save(becado); // Retorna el becado actualizado
}


    public Becado revocarBecado(Long id) {
        Becado becado = getBecadoById(id);
        becado.setEstadoBeca("Revocada");
        return becadoRepository.save(becado);
    }

    public void deleteBecado(Long id) {
        Becado becado = getBecadoById(id);
        becadoRepository.delete(becado);
    }

    public List<Becado> searchBecados(String query) {
        return becadoRepository.findByEstudianteNombreContainingOrEstudianteApellidoContaining(query, query);
    }

    // Método para aprobar un becado
    public Becado aprobarBeca(Long id) {
        Becado becado = getBecadoById(id);
        if ("Pendiente".equals(becado.getEstadoBeca())) {
            becado.setEstadoBeca("Activo");
            return becadoRepository.save(becado);
        } else {
            throw new IllegalStateException("La beca ya ha sido aprobada o rechazada.");
        }
    }

    // Nuevo método para rechazar un becado
    public Becado rechazarBeca(Long id) {
        Becado becado = getBecadoById(id);
        if ("Pendiente".equals(becado.getEstadoBeca())) {
            becado.setEstadoBeca("Rechazada");
            return becadoRepository.save(becado);
        } else {
            throw new IllegalStateException("La beca ya ha sido aprobada o rechazada.");
        }
    }
}
