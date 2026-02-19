package org.example.trabajodddspring.AgregadoJugador.Repositorio;

import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RepoJugador extends JpaRepository<Jugador, Integer> {
    List<Jugador> findByDireccionJuego_calleIgnoreCase(String calles);
}
