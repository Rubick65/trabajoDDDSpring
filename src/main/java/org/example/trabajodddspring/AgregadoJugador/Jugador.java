package org.example.trabajodddspring.AgregadoJugador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


// Anotaciones necesarias para poder parsear tanto Jugadores como clases hijo
@Entity
@Table(name = "Jugador")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_JUGADOR;// Id del jugador

    @Column(nullable = false, unique = true)
    private String DNI; // DNI del jugador
    @Column(nullable = false)
    private String nombre; // Nombre del jugador

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DIRECCION")
    private DireccionJuego direccionJuego;// Dirección del jugador

    /**
     * Constructor que da valores iniciales a los atributos de clase
     *
     * @param DNI            Dni de la persona
     * @param nombre         Nombre de la persona
     * @param direccionJuego Objeto dirección de juego que indica el lugar en donde el jugador va a jugar
     */
    public Jugador(String DNI, String nombre, DireccionJuego direccionJuego) {
        this.DNI = validarDNI(DNI);
        setNombre(nombre);
        setDireccionJuego(direccionJuego);
    }

    /**
     * Setter que comprueba que el nombre pasado como parámetro no esté vacío
     *
     * @param nombre Nombre del jugador
     */
    public void setNombre(String nombre) {
        if (nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre;
    }

    /**
     * Método que valida si el dni cumple el formato requerido
     *
     * @param DNI Dni a comprobar
     * @return Devuelve el dni en caso de ser válido
     */
    private String validarDNI(String DNI) throws IllegalArgumentException {
        // Si el dni es nulo o no cumple
        if (DNI == null || !DNI.matches("\\d{8}[A-Za-za]"))
            // Lanzamos una excepción
            throw new IllegalArgumentException("El DNI no cumple el formato");
        // Devolvemos el DNI en caso de que si se cumpla el formato
        return DNI;
    }

    /**
     * Compara un jugador con este por el DNI
     *
     * @param o the reference object with which to compare.
     * @return Devuleve true si son iguales y false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(DNI, jugador.DNI);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "ID_JUGADOR=" + ID_JUGADOR +
                ", DNI='" + DNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccionJuego=" + direccionJuego +
                '}';
    }
}
