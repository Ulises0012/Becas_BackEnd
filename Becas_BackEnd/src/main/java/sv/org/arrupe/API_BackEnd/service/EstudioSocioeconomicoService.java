package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.EstudioSocioeconomico;
import sv.org.arrupe.API_BackEnd.repository.EstudioSocioeconomicoRepository;

import java.util.List;

@Service
public class EstudioSocioeconomicoService {
    @Autowired
    private EstudioSocioeconomicoRepository estudioSocioeconomicoRepository;

    public List<EstudioSocioeconomico> getAllEstudiosSocioeconomicos() {
        return estudioSocioeconomicoRepository.findAllByOrderByIdEstudianteAsc();
    }

    public void eliminarEstudioSocioeconomico(Integer id) {
        estudioSocioeconomicoRepository.deleteById(id);
    }

    public EstudioSocioeconomico verEstudioSocioeconomico(Integer id) {
        return estudioSocioeconomicoRepository.findById(id).orElse(null);
    }
}
