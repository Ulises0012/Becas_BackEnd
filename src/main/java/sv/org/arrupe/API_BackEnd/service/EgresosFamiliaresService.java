package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.EgresosFamiliares;
import sv.org.arrupe.API_BackEnd.repository.EgresosFamiliaresRepository;
import sv.org.arrupe.API_BackEnd.exception.ResourceNotFoundException;

@Service
public class EgresosFamiliaresService {
    
    @Autowired
    private EgresosFamiliaresRepository egresosFamiliaresRepository;
    
    public EgresosFamiliares obtenerEgresosPorEstudiante(Long idEstudiante) {
        return egresosFamiliaresRepository.findByIdEstudiante(idEstudiante)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontraron egresos para el estudiante con ID: " + idEstudiante));
    }
    
    public EgresosFamiliares actualizarEgresos(Long idEstudiante, EgresosFamiliares egresosFamiliares) {
        EgresosFamiliares egresosExistente = obtenerEgresosPorEstudiante(idEstudiante);
        
        egresosExistente.setAlimentacion(egresosFamiliares.getAlimentacion());
        egresosExistente.setTransporte(egresosFamiliares.getTransporte());
        egresosExistente.setServiciosBasicos(egresosFamiliares.getServiciosBasicos());
        egresosExistente.setPagoVivienda(egresosFamiliares.getPagoVivienda());
        egresosExistente.setCotizacion(egresosFamiliares.getCotizacion());
        egresosExistente.setEducacion(egresosFamiliares.getEducacion());
        
        return egresosFamiliaresRepository.save(egresosExistente);
    }
}