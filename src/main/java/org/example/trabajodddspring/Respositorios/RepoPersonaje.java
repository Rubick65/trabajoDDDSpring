package org.example.trabajodddspring.Respositorios;

import org.example.trabajodddspring.AgregadoPersonaje.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoPersonaje extends JpaRepository<Personaje, Integer> {
    List<Personaje> findByClase(Personaje.Clase clase);

    // Query
    @Query("""
            select p
            from Personaje p
            where LOWER(p.jugador.direccionJuego) = LOWER(:dni)
            """)
    List<Personaje> findByJugadorDniIgnoreCase(@Param("DNI") String dni);

    // Native Query
    @NativeQuery(value = "SELECT * FROM PERSONAJE WHERE UPPER(SUBSTRING(nombrePersonaje, 1, 1)) = UPPER(?1)")
    List<Personaje> findByNombreIgnoreCase(char letra);


}