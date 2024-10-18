/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.API_BackEnd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.exception.ResourceNotFoundException;
import sv.org.arrupe.API_BackEnd.model.Becado;
import sv.org.arrupe.API_BackEnd.repository.BecadoRepository;

/**
 *
 * @author adria
 */
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

    public Becado updateBecado(Long id, Becado becadoDetails) {
        Becado becado = getBecadoById(id);
        
        becado.setEstudiante(becadoDetails.getEstudiante());
        becado.setTipoBeca(becadoDetails.getTipoBeca());
        becado.setFechaCreacion(becadoDetails.getFechaCreacion());
        becado.setMotivo(becadoDetails.getMotivo());
        becado.setEstadoBeca(becadoDetails.getEstadoBeca());

        return becadoRepository.save(becado);
    }

    public void deleteBecado(Long id) {
        Becado becado = getBecadoById(id);
        becadoRepository.delete(becado);
    }

    public List<Becado> searchBecados(String query) {
        return becadoRepository.findByEstudianteNombreContainingOrEstudianteApellidoContaining(query, query);
    }
}