package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.dto.ViviendaDTO;
import sv.org.arrupe.API_BackEnd.model.Vivienda;
import sv.org.arrupe.API_BackEnd.repository.ViviendaRepository;
import java.util.Optional;

@Service
public class ViviendaService {
    
    @Autowired
    private ViviendaRepository viviendaRepository;

    // Obtener vivienda por ID de estudiante
    public ViviendaDTO obtenerViviendaPorEstudiante(Long idEstudiante) {
        Optional<Vivienda> viviendaOpt = viviendaRepository.findByIdEstudiante(idEstudiante);
        if (viviendaOpt.isPresent()) {
            return convertirADTO(viviendaOpt.get());
        }
        return null;
    }

    // Crear o actualizar vivienda
    public ViviendaDTO guardarVivienda(ViviendaDTO viviendaDTO) {
        Vivienda vivienda = convertirAEntidad(viviendaDTO);
        vivienda = viviendaRepository.save(vivienda);
        return convertirADTO(vivienda);
    }

    // Método auxiliar para convertir entidad a DTO
    private ViviendaDTO convertirADTO(Vivienda vivienda) {
        ViviendaDTO dto = new ViviendaDTO();
        dto.setIdVivienda(vivienda.getIdVivienda());
        dto.setIdEstudiante(vivienda.getIdEstudiante());
        dto.setIdTipoVivienda(vivienda.getIdTipoVivienda());
        dto.setNumeroPersonas(vivienda.getNumeroPersonas());
        dto.setNumeroHabitaciones(vivienda.getNumeroHabitaciones());
        dto.setVehiculos(vivienda.getVehiculos());
        return dto;
    }

    // Método auxiliar para convertir DTO a entidad
    private Vivienda convertirAEntidad(ViviendaDTO dto) {
        Vivienda vivienda = new Vivienda();
        vivienda.setIdVivienda(dto.getIdVivienda());
        vivienda.setIdEstudiante(dto.getIdEstudiante());
        vivienda.setIdTipoVivienda(dto.getIdTipoVivienda());
        vivienda.setNumeroPersonas(dto.getNumeroPersonas());
        vivienda.setNumeroHabitaciones(dto.getNumeroHabitaciones());
        vivienda.setVehiculos(dto.getVehiculos());
        return vivienda;
    }
}