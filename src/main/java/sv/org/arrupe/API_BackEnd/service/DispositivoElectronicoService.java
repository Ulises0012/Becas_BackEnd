package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.DispositivoElectronico;
import sv.org.arrupe.API_BackEnd.model.TipoDispositivo;
import sv.org.arrupe.API_BackEnd.repository.DispositivoElectronicoRepository;
import sv.org.arrupe.API_BackEnd.repository.TipoDispositivoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DispositivoElectronicoService {
    private final DispositivoElectronicoRepository dispositivoRepository;
    private final TipoDispositivoRepository tipoDispositivoRepository;

    @Autowired
    public DispositivoElectronicoService(
            DispositivoElectronicoRepository dispositivoRepository,
            TipoDispositivoRepository tipoDispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
        this.tipoDispositivoRepository = tipoDispositivoRepository;
    }

    public List<DispositivoElectronico> getDispositivosByEstudiante(Long idEstudiante) {
        return dispositivoRepository.findByIdEstudiante(idEstudiante);
    }

    public Optional<DispositivoElectronico> getDispositivoById(Long id) {
        return dispositivoRepository.findById(id);
    }

    public DispositivoElectronico saveDispositivo(DispositivoElectronico dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public DispositivoElectronico updateDispositivo(DispositivoElectronico dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDispositivo(Long id) {
        dispositivoRepository.deleteById(id);
    }

    public List<TipoDispositivo> getAllTiposDispositivo() {
        return tipoDispositivoRepository.findAll();
    }
}