package com.example.hotel.Servicios;


import com.example.hotel.Entidades.Encuesta;

import com.example.hotel.repositorio.RepositorioEncuesta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioEncuesta {

    @Autowired
    private RepositorioEncuesta encuestaRepository;

    public List<Encuesta> listarEncuestas() {
        return encuestaRepository.findAll();
    }

    public Encuesta encontrarPorId(Long id) {
        return encuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encuesta no encontrada"));
    }


    public Encuesta saveOrUpdateEncuesta(Encuesta encuesta) {
        return encuestaRepository.save(encuesta);
    }


    public void deleteEncuesta(Long id) {
        encuestaRepository.deleteById(id);
    }


    // ... other methods remain unchanged ...


    public double promedioEdad() {
        // Implementación del cálculo del promedio de edad
        // Este es solo un ejemplo simplificado
        List<Encuesta> encuestas = listarEncuestas();
        double sumaEdades = 0;
        for (Encuesta encuesta : encuestas) {
            sumaEdades += encuesta.getEdad();
        }
        return sumaEdades / encuestas.size();
    }

    public List<Encuesta> filtrarEncuestas(String nivelSatisfaccion) {
        if (nivelSatisfaccion == null || nivelSatisfaccion.isEmpty()) {
            return encuestaRepository.findAll(); // Devuelve todas las encuestas si no hay filtro
        } else {
            return encuestaRepository.findByNivelSatisfaccion(nivelSatisfaccion);
        }
    }

}
