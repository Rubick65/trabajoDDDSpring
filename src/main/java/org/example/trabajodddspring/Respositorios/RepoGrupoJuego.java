package org.example.trabajodddspring.Respositorios;


import org.example.trabajodddspring.AgregadoGrupoJuego.GrupoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGrupoJuego extends JpaRepository<GrupoJuego, Integer> {
    //Modificadores y palabras clave
//    List<GrupoJuego> findByJugadores_Id_JUGADOR(Integer idJugador);
}
