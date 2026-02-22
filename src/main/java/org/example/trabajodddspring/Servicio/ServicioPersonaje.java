package org.example.trabajodddspring.Servicio;

import org.example.trabajodddspring.AgregadoPersonaje.Personaje;
import org.example.trabajodddspring.AgregadoPersonaje.Repositorio.RepoPersonaje;
import org.example.trabajodddspring.Interfaces.IRepositorioExtend;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioPersonaje implements IRepositorioExtend<Personaje, Integer> {

    private final RepoPersonaje repoPersonaje;

    public ServicioPersonaje(RepoPersonaje repoPersonaje) {
        this.repoPersonaje = repoPersonaje;
    }

    @Override
    public long count() {
        return this.repoPersonaje.count();
    }

    @Override
    public <S extends Personaje> S save(S entity) throws IOException, Exception {
        return this.repoPersonaje.save(entity);
    }

    @Override
    public void deleteById(Integer integer) throws IOException {
        this.repoPersonaje.deleteById(integer);
    }

    @Override
    public void deleteAll() throws IOException {
        this.repoPersonaje.deleteAll();
    }

    @Override
    public boolean existsById(Integer idJugador) throws IOException {
        return this.repoPersonaje.existsById(idJugador);
    }

    @Override
    public Personaje findById(Integer integer) throws IOException {
        return this.repoPersonaje.findById(integer).get();
    }

    @Override
    public List<Personaje> findAll() {
        return this.repoPersonaje.findAll();
    }

    @Override
    public Optional<Personaje> findByIdOptional(Integer idJugador) throws IOException {
        return this.repoPersonaje.findById(idJugador);
    }

    @Override
    public List<Personaje> findAllToList() throws IOException {
        return this.repoPersonaje.findAll();
    }

    public List<Personaje> buscarPersonajePorClase(Personaje.Clase clase) {
        return this.repoPersonaje.findByClase(clase);
    }

    public List<Personaje> buscarPorDniPersonajeJugador(String dni) {
        return this.repoPersonaje.findByJugadorDniIgnoreCase(dni);
    }

    public List<Personaje> buscarPorInicial(char letra) {
        return this.repoPersonaje.findByNombreIgnoreCase(letra);
    }


}
