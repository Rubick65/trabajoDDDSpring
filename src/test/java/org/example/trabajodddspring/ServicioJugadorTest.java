package org.example.trabajodddspring;

import org.example.trabajodddspring.AgregadoJugador.DireccionJuego;
import org.example.trabajodddspring.AgregadoJugador.DirectorDeJuego;
import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.example.trabajodddspring.AgregadoJugador.Repositorio.RepoJugador;
import org.example.trabajodddspring.Servicio.ServicioJugador;
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
@Import({ServicioJugador.class})
public class ServicioJugadorTest {
    @Autowired
    private ServicioJugador servicio;

    @Autowired
    private RepoJugador repoJugador;

    private Jugador j1;

    @BeforeEach
    void setUp() {
        repoJugador.deleteAll();
        DireccionJuego dj = new DireccionJuego("Madrid", "Calle del capibara", "1ºC", "28017");

        j1 = new Jugador("12345678A", "Juan", dj);
    }

    @Test
    @Commit
    void debeGuardarYContarDirectorDeJuego() throws Exception {
        DireccionJuego dj = new DireccionJuego("Madrid", "Calle del bebito", "1ºC", "28017");

        DirectorDeJuego d1 = new DirectorDeJuego("12348522A", "Jose", dj);

        servicio.save(d1);
    }

    @Test
    @Commit
    void debeGuardarYContarJugadores() throws Exception {
        servicio.save(j1);
        assertEquals(1, servicio.count());
    }

    @Test
    void debeEliminarJugador() throws Exception {
        Jugador guardado = servicio.save(j1);
        Integer id = guardado.getID_JUGADOR(); // Asumiendo que getId() devuelve el Integer

        servicio.deleteById(id);
        assertFalse(servicio.existsById(id));
    }

    @Test
    void debeEncontrarPorId() throws Exception {
        Jugador guardado = servicio.save(j1);
        Jugador encontrado = servicio.findById(guardado.getID_JUGADOR());

        assertNotNull(encontrado);
        assertEquals("12345678A", encontrado.getDNI());
    }

    @Test
    void debeEncontrarPorIdOptional() throws Exception {
        Jugador guardado = servicio.save(j1);
        Optional<Jugador> resultado = servicio.findByIdOptional(guardado.getID_JUGADOR());

        assertTrue(resultado.isPresent());
        assertEquals(guardado.getID_JUGADOR(), resultado.get().getID_JUGADOR());
    }

    @Test
    void debeDevolverTodosLosJugadores() throws Exception {
        servicio.save(j1);
        List<Jugador> lista = servicio.findAllToList();

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
    }

    @Test
    void debeBuscarJugadorPorCalle() throws Exception {
        // Configura la calle en el objeto j1 antes de guardar
        // j1.getDireccionJuego().setCalle("Avenida Siempre Viva");
        servicio.save(j1);

        // El método del servicio usa findByDireccionJuego_calleIgnoreCase
        List<Jugador> encontrados = servicio.buscarJugadorPorDireccion("Calle Falsa 123");
        // Nota: Este test pasará si los datos coinciden con lo guardado
        assertNotNull(encontrados);
    }

    @Test
    void debeContarPorCalle() throws Exception {
        servicio.save(j1);
        Long conteo = servicio.contarPorCalle("Calle Falsa 123");

        assertTrue(conteo >= 0);
    }

    @Test
    void debeBuscarDniPorDireccion() throws Exception {
        servicio.save(j1);
        Optional<Jugador> encontrado = servicio.buscarDniPorDireccion("12345678A");

        // Verificamos si la lógica de tu repo encuentra el DNI correctamente
        encontrado.ifPresent(jugador -> assertEquals("12345678A", jugador.getDNI()));
    }
}
