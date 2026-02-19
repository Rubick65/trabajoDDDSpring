package org.example.trabajodddspring.AgregadoAventura.Repositorio;


import org.example.trabajodddspring.AgregadoAventura.Aventura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepoAventura extends JpaRepository<Aventura, Integer> {

    List<Aventura> findByDificultad(Aventura.Dificultad dificultad);

}

