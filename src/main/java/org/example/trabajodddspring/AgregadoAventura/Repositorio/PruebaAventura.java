package org.example.trabajodddspring.AgregadoAventura.Repositorio;

import org.example.trabajodddspring.AgregadoAventura.Aventura;
import org.example.trabajodddspring.AgregadoAventura.AventuraAccion;
import org.example.trabajodddspring.AgregadoAventura.AventuraMisterio;
import org.example.trabajodddspring.Servicio.ServicioAventura;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PruebaAventura implements CommandLineRunner {

    private final ServicioAventura servicioAventura;

    public PruebaAventura(ServicioAventura servicioAventura) {
        this.servicioAventura = servicioAventura;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("Cantidad de jugadores en la base: " + servicioAventura.count());

            Aventura a1 = new Aventura("Prueba base",2, Aventura.Dificultad.FACIL);
            AventuraAccion a2 = new AventuraAccion("Prueba accion",4, Aventura.Dificultad.DIFICIL,54,3);
            AventuraMisterio a3 = new AventuraMisterio("Prueba Misterio",1, Aventura.Dificultad.NORMAL,"Enigma prueba");

            servicioAventura.save(a1);
            servicioAventura.save(a2);
            servicioAventura.save(a3);

            System.out.println("Lista de jugadores en la base:");
            servicioAventura.findAll().forEach(System.out::println);

        } catch (Exception e){
            System.err.println("Error al manejar aventuras: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
