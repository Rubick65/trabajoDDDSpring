package org.example.trabajodddspring.AgregadoJugador.Repositorio;

import org.example.trabajodddspring.AgregadoJugador.DireccionJuego;
import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.example.trabajodddspring.Servicio.ServicioJugador;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PruebaJugador implements CommandLineRunner {

    private final ServicioJugador servicioJugador;

    // Spring inyecta el servicio automáticamente
    public PruebaJugador(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("Cantidad de jugadores en la base: " + servicioJugador.count());

            // Crear una dirección de juego
            DireccionJuego direccionJuego = new DireccionJuego("Luna", "Jose manual", "1C", "29024");

            // Crear un jugador normal
            Jugador jugador1 = new Jugador("02790162D", "Pedro", direccionJuego);

            // Guardar jugador usando el servicio
            servicioJugador.save(jugador1);

            // Mostrar todos los jugadores guardados
            System.out.println("Lista de jugadores en la base:");
            servicioJugador.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error al manejar jugadores: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
