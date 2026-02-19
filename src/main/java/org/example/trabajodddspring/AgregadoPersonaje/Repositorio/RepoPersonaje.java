package org.example.trabajodddspring.AgregadoPersonaje.Repositorio;

import org.example.trabajodddspring.AgregadoPersonaje.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoPersonaje extends JpaRepository<Personaje, Integer> {
    List<Personaje> findByClaseIgnoreCase(Personaje.Clase clase);
}