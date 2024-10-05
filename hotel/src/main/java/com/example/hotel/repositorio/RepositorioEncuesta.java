package com.example.hotel.repositorio;

import com.example.hotel.Entidades.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface RepositorioEncuesta extends JpaRepository<Encuesta, Long> {


        List<Encuesta> findByNivelSatisfaccion(String nivelSatisfaccion);

    }



