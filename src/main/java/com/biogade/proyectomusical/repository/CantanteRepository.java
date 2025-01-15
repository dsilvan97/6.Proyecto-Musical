package com.biogade.proyectomusical.repository;

import com.biogade.proyectomusical.model.Cancion;
import com.biogade.proyectomusical.model.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CantanteRepository extends JpaRepository<Cantante, Long> {

    Optional<Cantante> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT s FROM Cantante c JOIN c.canciones s WHERE s.titulo ILIKE %:tituloCancion%")
    List<Cancion> cancionesPorTitulo(String tituloCancion);

}
