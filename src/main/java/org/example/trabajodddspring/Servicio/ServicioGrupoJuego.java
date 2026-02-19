package org.example.trabajodddspring.Servicio;

import org.example.trabajodddspring.AgregadoAventura.Aventura;
import org.example.trabajodddspring.AgregadoGrupoJuego.GrupoJuego;
import org.example.trabajodddspring.AgregadoGrupoJuego.Repositorio.RepoGrupoJuego;
import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.example.trabajodddspring.Interfaces.IRepositorioExtend;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioGrupoJuego implements IRepositorioExtend<GrupoJuego, Integer> {

    private final RepoGrupoJuego repoGrupoJuego;

    public ServicioGrupoJuego(RepoGrupoJuego repoGrupoJuego) {
        this.repoGrupoJuego = repoGrupoJuego;
    }

    @Override
    public long count() {
        return this.repoGrupoJuego.count();
    }

    @Override
    public <S extends GrupoJuego> S save(S entity) throws Exception {
        return this.repoGrupoJuego.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.repoGrupoJuego.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repoGrupoJuego.deleteAll();
    }

    @Override
    public boolean existsById(Integer idGrupoJuego) {
        return this.repoGrupoJuego.existsById(idGrupoJuego);
    }

    @Override
    public GrupoJuego findById(Integer idGrupoJuego) {
        return this.repoGrupoJuego.findById(idGrupoJuego).get();
    }

    @Override
    public List<GrupoJuego> findAll() {
        return this.repoGrupoJuego.findAll();
    }

    @Override
    public Optional<GrupoJuego> findByIdOptional(Integer idGrupoJuego) {
        return this.repoGrupoJuego.findById(idGrupoJuego);
    }

    @Override
    public List<GrupoJuego> findAllToList() {
        return this.repoGrupoJuego.findAll();
    }

    public List<GrupoJuego> buscarPorIdJugadores(Integer idJugador) {
        return this.repoGrupoJuego.findByJugadores_Id_JUGADOR(idJugador);
    }

}
