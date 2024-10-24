package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.ServiciosFamiliares;
import sv.org.arrupe.API_BackEnd.repository.ServiciosFamiliaresRepository;
import sv.org.arrupe.API_BackEnd.dto.ServiciosFamiliaresDTO;
import sv.org.arrupe.API_BackEnd.exception.ResourceNotFoundException;

import java.math.BigDecimal;

@Service
public class ServiciosFamiliaresService {

    @Autowired
    private ServiciosFamiliaresRepository serviciosFamiliaresRepository;

    public ServiciosFamiliares obtenerPorIdEstudiante(Long idEstudiante) {
        return serviciosFamiliaresRepository.findByIdEstudiante(idEstudiante)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontraron servicios familiares para el estudiante con ID: " + idEstudiante));
    }

    public ServiciosFamiliares actualizarServiciosFamiliares(Long idEstudiante, ServiciosFamiliaresDTO serviciosDTO) {
        ServiciosFamiliares servicios = serviciosFamiliaresRepository.findByIdEstudiante(idEstudiante)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontraron servicios familiares para el estudiante con ID: " + idEstudiante));

        // Actualizar los campos usando BigDecimal
        servicios.setLuz(serviciosDTO.getLuz() != null ? serviciosDTO.getLuz() : BigDecimal.ZERO);
        servicios.setAgua(serviciosDTO.getAgua() != null ? serviciosDTO.getAgua() : BigDecimal.ZERO);
        servicios.setCelular(serviciosDTO.getCelular() != null ? serviciosDTO.getCelular() : BigDecimal.ZERO);
        servicios.setDatosMoviles(serviciosDTO.getDatosMoviles() != null ? serviciosDTO.getDatosMoviles() : BigDecimal.ZERO);
        servicios.setCableEInternet(serviciosDTO.getCableEInternet() != null ? serviciosDTO.getCableEInternet() : BigDecimal.ZERO);

        return serviciosFamiliaresRepository.save(servicios);
    }
}
