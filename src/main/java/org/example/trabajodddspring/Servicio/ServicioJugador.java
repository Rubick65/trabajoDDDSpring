package org.example.trabajodddspring.Servicio;

import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.example.trabajodddspring.AgregadoJugador.Repositorio.RepoJugador;
import org.example.trabajodddspring.Interfaces.IRepositorioExtend;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioJugador implements IRepositorioExtend<Jugador, Integer> {

    private final RepoJugador repoJugador;

    public ServicioJugador(RepoJugador repoJugador) {
        this.repoJugador = repoJugador;
    }

    @Override
    public long count() {
        return this.repoJugador.count();
    }

    @Override
    public <S extends Jugador> S save(S entity) throws IOException, Exception {
        return this.repoJugador.save(entity);
    }

    @Override
    public void deleteById(Integer integer) throws IOException {
        this.repoJugador.deleteById(integer);
    }

    @Override
    public void deleteAll() throws IOException {
        this.repoJugador.deleteAll();
    }

    @Override
    public boolean existsById(Integer idJugador) throws IOException {
        return this.repoJugador.existsById(idJugador);
    }

    @Override
    public Jugador findById(Integer integer) throws IOException {
        return this.repoJugador.findById(integer).get();
    }

    @Override
    public List<Jugador> findAll() {
        return this.repoJugador.findAll();
    }

    @Override
    public Optional<Jugador> findByIdOptional(Integer idJugador) throws IOException {
        return this.repoJugador.findById(idJugador);
    }

    @Override
    public List<Jugador> findAllToList() throws IOException {
        return this.repoJugador.findAll();
    }

    public List<Jugador> buscarJugadorPorDireccion(String calle) {
        return this.repoJugador.findByDireccionJuego_calleIgnoreCase(calle);
    }

    public Optional<Jugador> buscarDniPorDireccion(String dni) {
        return this.repoJugador.findByDniConDireccion(dni);
    }

    public Long contarPorCalle(String calle) {
        return this.repoJugador.countByCalle(calle);
    }

}
