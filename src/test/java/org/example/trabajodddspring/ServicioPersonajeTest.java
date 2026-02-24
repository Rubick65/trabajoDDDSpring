package org.example.trabajodddspring;

import org.example.trabajodddspring.AgregadoPersonaje.ObjetoInventario;
import org.example.trabajodddspring.AgregadoPersonaje.Personaje;
import org.example.trabajodddspring.Servicio.GestorInventario;
import org.example.trabajodddspring.Servicio.ServicioPersonaje;
import org.example.trabajodddspring.Respositorios.RepoPersonaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ServicioPersonaje.class, GestorInventario.class})
public class ServicioPersonajeTest {

    @Autowired
    private ServicioPersonaje servicio;

    @Autowired
    private RepoPersonaje repoPersonaje;

    @Autowired
    private GestorInventario gestorInventario;

    private Personaje p1;

    @BeforeEach
    void setUp() {
        repoPersonaje.deleteAll();

        // Personaje de prueba
        p1 = new Personaje(new ArrayList<>(), 70.5, "Bartolomeo", "Feo", "Se perdio",
                Personaje.Clase.CLERIGO, Personaje.Raza.ORCO);
        gestorInventario = new GestorInventario();
    }

    @Test
    void debeGuardarYContarPersonajes() throws Exception {
        servicio.save(p1);
        assertEquals(1, servicio.count());
    }

    @Test
    @Commit
    void debeGuardarObjetoAlInventario() throws Exception {
        repoPersonaje.deleteAll();
        ObjetoInventario porra = new ObjetoInventario("Porra", 30, "Un palo que sirve para pegar fuerte");
        gestorInventario.agregarObjeto(p1, porra);
        Personaje guardado = servicio.save(p1);
    }


    @Test
    void debeBuscarPorClase() throws Exception {
        servicio.save(p1);
        List<Personaje> encontrados = servicio.buscarPersonajePorClase(Personaje.Clase.CLERIGO);
        assertFalse(encontrados.isEmpty());
        assertEquals("Bartolomeo", encontrados.get(0).getNombrePersonaje());
    }

    @Test
    void debeEliminarPersonaje() throws Exception {
        Personaje guardado = servicio.save(p1);
        servicio.deleteById(guardado.getID_PERSONAJE());
        assertFalse(servicio.existsById(guardado.getID_PERSONAJE()));
    }

    @Test
    void debeEncontrarPorIdOptional() throws Exception {
        Personaje guardado = servicio.save(p1);
        Optional<Personaje> resultado = servicio.findByIdOptional(guardado.getID_PERSONAJE());
        assertTrue(resultado.isPresent());
    }

    @Test
    void debeDevolverTodosLosPersonajes() throws Exception {
        servicio.save(p1);
        List<Personaje> personajes = servicio.findAllToList();
        personajes.forEach(System.out::println);
        assertFalse(personajes.isEmpty());
    }
    
    @Test
    void debeDevolverPersonajeInicial() throws Exception {
        servicio.save(p1);
        List<Personaje> personajes = servicio.buscarPorInicial('B');
        personajes.forEach(System.out::println);
        assertFalse(personajes.isEmpty());
    }

    @Test
    void debeNoDevolverPersonajeInicial() throws Exception {
        servicio.save(p1);
        List<Personaje> personajes = servicio.buscarPorInicial('A');
        assertTrue(personajes.isEmpty());
    }

}
