package org.example.trabajodddspring.AgregadoAventura.Repositorio;


import org.example.trabajodddspring.AgregadoAventura.Aventura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoAventura extends JpaRepository<Aventura, Integer> {

    //Modificadores y palabras clave
    List<Aventura> findByDificultad(Aventura.Dificultad dificultad);

    //Query
    @Query("select a from Aventura a where a.duracionSesionesAprox = :duracion")
    List<Aventura> findByDuracion(@Param("duracion") int duracion);

    //Native Query
    @NativeQuery(value = "SELECT * FROM AVENTURA WHERE nombreAventura = ?1")
    Aventura findByNombreAventura(String nombre);

}

