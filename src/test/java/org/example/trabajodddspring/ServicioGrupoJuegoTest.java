package org.example.trabajodddspring;

import org.example.trabajodddspring.AgregadoGrupoJuego.GrupoJuego;
import org.example.trabajodddspring.AgregadoGrupoJuego.Repositorio.RepoGrupoJuego;
import org.example.trabajodddspring.AgregadoPersonaje.Personaje;
import org.example.trabajodddspring.Servicio.GestorInventario;
import org.example.trabajodddspring.Servicio.ServicioAventura;
import org.example.trabajodddspring.Servicio.ServicioGrupoJuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ServicioGrupoJuego.class})

public class ServicioGrupoJuegoTest {

    @Autowired
    private ServicioGrupoJuego servicio;

    @Autowired
    private RepoGrupoJuego repoGrupoJuego;

    private GrupoJuego g1;

    @BeforeEach
    void setUp() throws IOException {
        repoGrupoJuego.deleteAll();
        g1 = new GrupoJuego("Los macarrones","Nos gustan los macarrones",new ArrayList<>());
    }

    @Test
    void debeGuardarYContarGrupo() throws Exception {
        servicio.save(g1);
        assertEquals(1, servicio.count());
    }

    @Test
    void debeEliminarPersonaje() throws Exception {
        GrupoJuego guardado = servicio.save(g1);
        servicio.deleteById(guardado.getID_GRUPO());
        assertFalse(servicio.existsById(guardado.getID_GRUPO()));
    }

    @Test
    void debeEncontrarPorIdOptional() throws Exception {
        GrupoJuego guardado = servicio.save(g1);
        Optional<GrupoJuego> resultado = servicio.findByIdOptional(guardado.getID_GRUPO());
        assertTrue(resultado.isPresent());
    }

    @Test
    void debeDevolverTodosLosGrupos() throws Exception {
        servicio.save(g1);
        List<GrupoJuego> grupos = servicio.findAllToList();
        grupos.forEach(System.out::println);
        assertFalse(grupos.isEmpty());
    }



}
