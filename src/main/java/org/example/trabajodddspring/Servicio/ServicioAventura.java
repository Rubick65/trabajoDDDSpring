package org.example.trabajodddspring.Servicio;

import org.example.trabajodddspring.AgregadoAventura.Aventura;
import org.example.trabajodddspring.AgregadoAventura.Repositorio.RepoAventura;
import org.example.trabajodddspring.Interfaces.IRepositorioExtend;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAventura implements IRepositorioExtend<Aventura, Integer> {

    private final RepoAventura repoAventura;

    public ServicioAventura(RepoAventura repoAventura) {
        this.repoAventura = repoAventura;
    }

    @Override
    public long count() {
        return this.repoAventura.count();
    }

    @Override
    public <S extends Aventura> S save(S entity) throws Exception {
        return this.repoAventura.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.repoAventura.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repoAventura.deleteAll();
    }

    @Override
    public boolean existsById(Integer idAventura) {
        return this.repoAventura.existsById(idAventura);
    }

    @Override
    public Aventura findById(Integer idAventura) {
        return this.repoAventura.findById(idAventura).get();
    }

    @Override
    public List<Aventura> findAll() {
        return this.repoAventura.findAll();
    }

    @Override
    public Optional<Aventura> findByIdOptional(Integer idAventura) {
        return this.repoAventura.findById(idAventura);
    }

    @Override
    public List<Aventura> findAllToList() {
        return this.repoAventura.findAll();
    }

    //Consulta con modificadores y palabras clave
    public List<Aventura> buscarPorDificultad(Aventura.Dificultad dificultad) {
        return this.repoAventura.findByDificultad(dificultad);
    }

    //Consulta con NativeQuery
    public Long contarPorDuracion(int duracion) {
        return this.repoAventura.countByDuracion(duracion);
    }

    //Consulta con Query
    public Aventura buscarPorNombre(String nombreAventura) {
        return this.repoAventura.findByNombreAventura(nombreAventura);
    }
}
