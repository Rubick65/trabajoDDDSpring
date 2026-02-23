package org.example.trabajodddspring.AgregadoGrupoJuego;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.trabajodddspring.AgregadoJugador.Jugador;
import org.example.trabajodddspring.AgregadoJugador.Repositorio.RepoJugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "GrupoJuego",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombreGrupo"})
        }
)
@Getter
@Setter
@NoArgsConstructor
public class GrupoJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_GRUPO;

    @Column(unique = true, nullable = false)
    private String nombreGrupo;

    @Column(nullable = false)
    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "JUGADOR_GRUPOJUEGO",
            joinColumns = @JoinColumn(name = "ID_GRUPOJUEGO"),
            inverseJoinColumns = @JoinColumn(name = "ID_JUGADOR")
    )
    private List<Jugador> listaMiembros = new ArrayList<>();

    /**
     * Constructor que da valores iniciales a los atributos de clase
     *
     * @param nombreGrupo   Nombre del grupo
     * @param descripcion   Descripción del grupo
     * @param listaMiembros Lista de miembros iniciales del grupo
     * @throws IOException lanza la excepción si ocurre algún problema al intentar comprobar la existencia de los jugadores
     */
    public GrupoJuego(String nombreGrupo, String descripcion, List<Jugador> listaMiembros) throws IOException {
        setNombreGrupo(nombreGrupo);
        setDescripcion(descripcion);
        inicializarMiembros(listaMiembros);
    }

    public GrupoJuego(int idGrupo,String nombreGrupo, String descripcion, List<Jugador> listaMiembros) throws IOException {
        this.ID_GRUPO = idGrupo;
        setNombreGrupo(nombreGrupo);
        setDescripcion(descripcion);
        inicializarMiembros(listaMiembros);
    }

    /**
     * Comprueba que los jugadores sean válido y existan
     *
     * @param listaMiembros lista de miembros a comprobar
     * @throws IOException Lanza excepción en caso de problemas en la lectura de los juagadores
     */
    public void inicializarMiembros(List<Jugador> listaMiembros) throws IOException {
        if (listaMiembros.isEmpty())
            throw new IllegalArgumentException("La lista de jugadores debe tener por lo menos un jugador");

        this.listaMiembros.clear();
        for (Jugador j : listaMiembros) {
            agregarJugador(j);
        }
    }

    /**
     * Comprueba la existencia del jugador que se quiere añadir
     *
     * @param jugador jugador a añadir
     */
    public void agregarJugador(Jugador jugador) throws IOException {
        listaMiembros.add(jugador);
    }

    /**
     * Elimina un jugador de la lista
     *
     * @param jugador jugador a eliminar
     * @return True si lo pudo eliminar y false en caso contrario
     */
    public boolean eliminarJugador(Jugador jugador) {
        if (listaMiembros.size() == 1)
            throw new IllegalArgumentException("No puedes eliminar todos los jugadores del grupo " + this.getNombreGrupo() + " con ID " + this.getID_GRUPO() + ", primero elimina el grupo");

        return listaMiembros.removeIf(j -> j == jugador);
    }


    /**
     * Compara un objeto externo con este a traves de todos sus atributos
     *
     * @param o the reference object with which to compare.
     * @return True en caso de que coincidan los objetos y false en caso contrario
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GrupoJuego that = (GrupoJuego) o;
        return ID_GRUPO == that.ID_GRUPO && Objects.equals(nombreGrupo, that.nombreGrupo) && Objects.equals(descripcion, that.descripcion) && Objects.equals(listaMiembros, that.listaMiembros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_GRUPO, nombreGrupo, descripcion, listaMiembros);
    }

    @Override
    public String toString() {
        return "GrupoJuego{" +
                "ID_GRUPO=" + ID_GRUPO +
                ", nombreGrupo='" + nombreGrupo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", listaMiembros=" + listaMiembros +
                '}';
    }
}
