package org.example.trabajodddspring;

import org.example.trabajodddspring.AgregadoAventura.Aventura;
import org.example.trabajodddspring.AgregadoAventura.Repositorio.RepoAventura;
import org.example.trabajodddspring.AgregadoPersonaje.Personaje;
import org.example.trabajodddspring.Servicio.ServicioAventura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ServicioAventura.class})

public class ServicioAventuraTest {
    @Autowired
    private ServicioAventura servicio;

    @Autowired
    private RepoAventura repoAventura;

    private Aventura a1;

    @BeforeEach
    void setUp() {
        repoAventura.deleteAll();

        a1 = new Aventura("Madrazo",3, Aventura.Dificultad.NORMAL);
    }

    @Test
    void debeGuardarYContarAventuras() throws Exception {
        servicio.save(a1);
        assertEquals(1, servicio.count());
    }

    @Test
    void debeEliminarAventuras() throws Exception {
        Aventura guardado = servicio.save(a1);
        servicio.deleteById(guardado.getID_AVENTURA());
        assertFalse(servicio.existsById(guardado.getID_AVENTURA()));
    }

    @Test
    void debeEncontrarPorIdOptional() throws Exception {
        Aventura guardado = servicio.save(a1);
        Optional<Aventura> resultado = servicio.findByIdOptional(guardado.getID_AVENTURA());
        assertTrue(resultado.isPresent());
    }

    @Test
    void debeDevolverTodosLosAventuras() throws Exception {
        servicio.save(a1);
        List<Aventura> aventuras = servicio.findAllToList();
        aventuras.forEach(System.out::println);
        assertFalse(aventuras.isEmpty());
    }

    @Test
    void debeDevolverDificultad() throws Exception {
        servicio.save(a1);
        List<Aventura> aventuras = servicio.buscarPorDificultad(a1.getDificultad());
        aventuras.forEach(System.out::println);
        assertFalse(aventuras.isEmpty());
    }

    @Test
    void debeDevolverDuracion() throws Exception {
        servicio.save(a1);
        List<Aventura> aventuras = servicio.buscarPorDuracion(a1.getDuracionSesionesAprox());
        aventuras.forEach(System.out::println);
        assertFalse(aventuras.isEmpty());
    }

    @Test
    void debeDevolverNombreAventura() throws Exception {
        servicio.save(a1);
        Aventura aven = servicio.buscarPorNombre(a1.getNombreAventura());
        System.out.println(aven);
        assertNotNull(aven);
    }

}
