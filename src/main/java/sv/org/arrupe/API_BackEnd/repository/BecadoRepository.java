/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.API_BackEnd.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.org.arrupe.API_BackEnd.model.Becado;

/**
 *
 * @author adria
 */
@Repository
public interface BecadoRepository extends JpaRepository<Becado, Long> {
    List<Becado> findByEstudianteNombreContainingOrEstudianteApellidoContaining(String nombre, String apellido);
}