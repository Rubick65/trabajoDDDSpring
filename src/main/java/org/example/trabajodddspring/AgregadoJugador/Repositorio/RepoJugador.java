package org.example.trabajodddspring.AgregadoJugador.Repositorio;

import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepoJugador extends JpaRepository<Jugador, Integer> {
    // Modificadores y palabras clave
    List<Jugador> findByDireccionJuego_calleIgnoreCase(String calles);

    //Query
    List<Jugador> findBy(String nome);

    @Query("SELECT j FROM Jugador j JOIN FETCH j.direccionJuego WHERE j.DNI = :dni")
    Optional<Jugador> findByDniConDireccion(@Param("dni") String dni);

    @NativeQuery
            (value = """
                    SELECT COUNT(*)
                    FROM Jugador j
                    JOIN DireccionJuego d ON j.ID_DIRECCION = d.ID_DIRECCION
                    WHERE d.calle = ?1
                    """)
    Long countByCalle(String calle);

}
