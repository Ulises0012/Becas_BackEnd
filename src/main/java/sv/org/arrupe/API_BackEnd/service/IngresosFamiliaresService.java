package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.IngresosFamiliares;
import sv.org.arrupe.API_BackEnd.repository.IngresosFamiliaresRepository;

import java.util.Optional;

@Service
public class IngresosFamiliaresService {

    @Autowired
    private IngresosFamiliaresRepository ingresosFamiliaresRepository;

    public Optional<IngresosFamiliares> getIngresosByEstudiante(Long idEstudiante) {
        return ingresosFamiliaresRepository.findByIdEstudiante(idEstudiante);
    }

    public IngresosFamiliares updateIngresosFamiliares(Long idEstudiante, IngresosFamiliares ingresos) {
        Optional<IngresosFamiliares> existingIngresos = ingresosFamiliaresRepository.findByIdEstudiante(idEstudiante);

        if (existingIngresos.isPresent()) {
            IngresosFamiliares updateIngresos = existingIngresos.get();
            updateIngresos.setIngresoTotal(ingresos.getIngresoTotal());
            updateIngresos.setNegocioPropio(ingresos.getNegocioPropio());
            updateIngresos.setDireccionNegocio(ingresos.getDireccionNegocio());
            updateIngresos.setIngresoPerCapita(ingresos.getIngresoPerCapita());

            return ingresosFamiliaresRepository.save(updateIngresos);
        }

        // Si no existe, crear uno nuevo
        ingresos.setIdEstudiante(idEstudiante.intValue());
        return ingresosFamiliaresRepository.save(ingresos);
    }
}
