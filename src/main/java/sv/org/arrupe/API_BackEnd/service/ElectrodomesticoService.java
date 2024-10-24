package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.Electrodomestico;
import sv.org.arrupe.API_BackEnd.model.TipoElectrodomestico;
import sv.org.arrupe.API_BackEnd.repository.ElectrodomesticoRepository;
import sv.org.arrupe.API_BackEnd.repository.TipoElectrodomesticoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ElectrodomesticoService {
    private final ElectrodomesticoRepository electrodomesticoRepository;
    private final TipoElectrodomesticoRepository tipoElectrodomesticoRepository;

    @Autowired
    public ElectrodomesticoService(
            ElectrodomesticoRepository electrodomesticoRepository,
            TipoElectrodomesticoRepository tipoElectrodomesticoRepository) {
        this.electrodomesticoRepository = electrodomesticoRepository;
        this.tipoElectrodomesticoRepository = tipoElectrodomesticoRepository;
    }

    // Método para obtener todos los electrodomésticos por estudiante
    public List<Electrodomestico> getElectrodomesticosByEstudiante(Long idEstudiante) {
        return electrodomesticoRepository.findByIdEstudiante(idEstudiante); // Cambiado a Long
    }

    // Método para obtener un electrodoméstico por ID
    public Optional<Electrodomestico> getElectrodomesticoById(Long id) {
        return electrodomesticoRepository.findById(id); // Cambiado a Long
    }

    // Método para guardar un electrodoméstico
    public Electrodomestico saveElectrodomestico(Electrodomestico electrodomestico) {
        return electrodomesticoRepository.save(electrodomestico);
    }

    // Método para actualizar un electrodoméstico
    public Electrodomestico updateElectrodomestico(Electrodomestico electrodomestico) {
        return electrodomesticoRepository.save(electrodomestico);
    }

    // Método para eliminar un electrodoméstico
    public void deleteElectrodomestico(Long id) {
        electrodomesticoRepository.deleteById(id); // Cambiado a Long
    }

    // Método para obtener todos los tipos de electrodomésticos
    public List<TipoElectrodomestico> getAllTiposElectrodomestico() {
        return tipoElectrodomesticoRepository.findAll();
    }
}
