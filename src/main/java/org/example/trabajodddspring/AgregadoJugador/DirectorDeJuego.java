package org.example.trabajodddspring.AgregadoJugador;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.trabajodddspring.AgregadoAventura.Aventura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "ID_JUGADOR")
public class DirectorDeJuego extends Jugador {

    @OneToMany( cascade = CascadeType.ALL)
    @JoinTable(
            name = "DirectorAventura",
            joinColumns = @JoinColumn(name = "ID_DIRECTOR"),
            inverseJoinColumns = @JoinColumn(name = "ID_AVENTURA")
    )
    private List<Aventura> listaAventuras = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "ID_AVENTURA", nullable = true)
    private Aventura aventuraSeleccionada;

    /**
     * Constructor que da valores iniciales a los atributos de clase
     *
     * @param DNI            Dni de la persona
     * @param nombre         Nombre de la persona
     * @param direccionJuego Objeto dirección de juego que indica el lugar en donde el jugador va a jugar
     * @param listaAventuras Lista con los ids de la o las aventuras que el director de juego está dirigiendo
     *
     */
    public DirectorDeJuego(String DNI, String nombre, DireccionJuego direccionJuego, List<Aventura> listaAventuras) {
        super(DNI, nombre, direccionJuego);
        setListaAventuras(listaAventuras);
    }

    /**
     * Constructor que da valores iniciales a los atributos de clase sin la lista de aventuras
     *
     * @param DNI            Dni de la persona
     * @param nombre         Nombre de la persona
     * @param direccionJuego Objeto dirección de juego que indica el lugar en donde el jugador va a jugar
     *
     */
    public DirectorDeJuego(String DNI, String nombre, DireccionJuego direccionJuego) {
        super(DNI, nombre, direccionJuego);
    }

    /**
     * Agrega una aventura al director de juego si y solo si la aventura existe
     *
     * @param idAventura id de la aventura a agregar
     */
    public void agregarAventura(Aventura idAventura) throws IOException {
        listaAventuras.add(idAventura);
    }


    /**
     * Método que elimina una aventura de la lista de aventuras guardadas del director
     *
     * @param idAventura Id de la aventura ha eliminar
     * @throws Exception Lanza una excepción si la aventura a eliminar no existe
     */
    public void eliminarAventura(int idAventura) throws Exception {
        comprobarAventura(idAventura);
        listaAventuras.remove(idAventura);
    }

    /**
     * Método que comprueba si el id de una aventura existe en la lista de aventuras guardadas por el director
     *
     * @param idAventura Id de la aventura que se va a comprobar
     * @throws Exception Lanza una excepción en caso de que la aventura no exista en la lista de aventuras guardadas
     */
    private void comprobarAventura(int idAventura) throws Exception {
        if (!listaAventuras.contains(idAventura)) throw new Exception("El director no posee esta aventura");
    }

    @Override
    public String toString() {
        return super.toString() + "DirectorDeJuego{" +
                "listaAventuras=" + listaAventuras +
                ", aventuraSeleccionada=" + aventuraSeleccionada +
                '}';
    }
}
