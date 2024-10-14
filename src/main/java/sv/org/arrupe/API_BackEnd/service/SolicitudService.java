/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.API_BackEnd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.Solicitud;
import sv.org.arrupe.API_BackEnd.repository.SolicitudRepository;

/**
 *
 * @author adria
 */
@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;
    
    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAllByOrderByEstudianteApellidoAsc();
    }
    
    public void eliminarSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }
    
    public Solicitud verSolicitud(Long id) {
        return solicitudRepository.findById(id).orElse(null);
    }
}