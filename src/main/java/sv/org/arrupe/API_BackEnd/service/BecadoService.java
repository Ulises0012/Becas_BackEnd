package sv.org.arrupe.API_BackEnd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.exception.ResourceNotFoundException;
import sv.org.arrupe.API_BackEnd.model.Becado;
import sv.org.arrupe.API_BackEnd.model.TipoBeca;
import sv.org.arrupe.API_BackEnd.repository.BecadoRepository;

@Service
public class BecadoService {

    @Autowired
    private BecadoRepository becadoRepository;

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

    public Becado actualizarSoloTipoBeca(Long id, TipoBeca nuevoTipoBeca) {
        Becado becado = getBecadoById(id); // Busca al becado por su ID
        becado.setTipoBeca(nuevoTipoBeca); // Solo actualiza el tipo de beca
        return becadoRepository.save(becado); // Guarda los cambios
    }

    public Becado revocarBecado(Long id) {
        Becado becado = getBecadoById(id); // Busca al becado por su ID
        becado.setEstadoBeca("Revocada"); // Cambia el estado de la beca a "Revocada"
        return becadoRepository.save(becado); // Guarda los cambios
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
        Becado becado = getBecadoById(id); // Busca al becado por su ID
        if ("Pendiente".equals(becado.getEstadoBeca())) {
            becado.setEstadoBeca("Activo"); // Cambia el estado a "Activo"
            return becadoRepository.save(becado); // Guarda los cambios
        } else {
            throw new IllegalStateException("La beca ya ha sido aprobada o rechazada.");
        }
    }

    // Nuevo método para rechazar un becado
    public Becado rechazarBeca(Long id) {
        Becado becado = getBecadoById(id); // Busca al becado por su ID
        if ("Pendiente".equals(becado.getEstadoBeca())) {
            becado.setEstadoBeca("Rechazada"); // Cambia el estado a "Rechazada"
            return becadoRepository.save(becado); // Guarda los cambios
        } else {
            throw new IllegalStateException("La beca ya ha sido aprobada o rechazada.");
        }
    }
}
