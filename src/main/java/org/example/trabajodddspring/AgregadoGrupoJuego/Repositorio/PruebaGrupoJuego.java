package org.example.trabajodddspring.AgregadoGrupoJuego.Repositorio;

import org.example.trabajodddspring.AgregadoGrupoJuego.GrupoJuego;
import org.example.trabajodddspring.AgregadoJugador.DireccionJuego;
import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.example.trabajodddspring.Servicio.ServicioGrupoJuego;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PruebaGrupoJuego implements CommandLineRunner  {

    private final ServicioGrupoJuego servicioGrupoJuego;

    public PruebaGrupoJuego(ServicioGrupoJuego servicioGrupoJuego) {
        this.servicioGrupoJuego = servicioGrupoJuego;
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            DireccionJuego direccionJuego = new DireccionJuego("Luna", "Jose manual", "1C", "29024");
            Jugador jugador1 = new Jugador("02790162D", "Pedro", direccionJuego);
            Jugador jugador2 = new Jugador("04490162D", "Atolfo", direccionJuego);

            GrupoJuego g1 = new GrupoJuego("Los albondigas","Nos gustan las albondigas", new ArrayList<Jugador>());

            g1.agregarJugador(jugador1);
            g1.agregarJugador(jugador2);

            servicioGrupoJuego.save(g1);

            System.out.println("Lista de grupos de juegos en la base:");
            servicioGrupoJuego.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error al manejar Grupos de juegos: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
