package org.example.trabajodddspring.AgregadoGrupoJuego.Repositorio;


import org.example.trabajodddspring.AgregadoGrupoJuego.GrupoJuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RepoGrupoJuego extends JpaRepository<GrupoJuego, Integer> {
    List<GrupoJuego> findByJugadores_Id_JUGADOR(Integer idJugador);
}
