package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.TipoBeca;
import sv.org.arrupe.API_BackEnd.repository.TipoBecaRepository;

import java.util.List;

@Service
public class TipoBecaService {

    @Autowired
    private TipoBecaRepository tipoBecaRepository;

    public List<TipoBeca> obtenerTiposDeBeca() {
        return tipoBecaRepository.findAll();
    }
}
