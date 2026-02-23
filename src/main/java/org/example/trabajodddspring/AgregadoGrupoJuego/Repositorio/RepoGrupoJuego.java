package org.example.trabajodddspring.AgregadoGrupoJuego.Repositorio;


import org.example.trabajodddspring.AgregadoGrupoJuego.GrupoJuego;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepoGrupoJuego extends JpaRepository<GrupoJuego, Integer> {
    //Modificadores y palabras clave
//    List<GrupoJuego> findByJugadores_Id_JUGADOR(Integer idJugador);
}
