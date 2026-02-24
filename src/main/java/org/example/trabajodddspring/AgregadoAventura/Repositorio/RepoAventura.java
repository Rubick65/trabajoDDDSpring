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

    //Native Query
    @NativeQuery(value = """
            SELECT a
            FROM Aventura a
            JOIN AventuraAccion ac on a.ID_AVENTURA = ac.ID_AVENTURA
            JOIN AventuraMisterio am on a.ID_AVENTURA = am.ID_AVENTURA
            WHERE duracionSesionesAprox = ?1
            """)
    List<Aventura> findByDuracion(int duracion);

    //Query
    @Query("SELECT a FROM Aventura a WHERE a.nombreAventura = :nombre")
    Aventura findByNombreAventura(@Param("nombre") String nombre);

}

