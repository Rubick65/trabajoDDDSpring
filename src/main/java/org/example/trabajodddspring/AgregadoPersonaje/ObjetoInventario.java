package org.example.trabajodddspring.AgregadoPersonaje;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Entity
@Table(
        name = "ObjetoInventario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nombre", "descripcionObjeto"})
        }
)
@Getter
@Setter
@NoArgsConstructor
public class ObjetoInventario {

    public enum Categoria {
        MALDITO, MAGICO, NORMAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_OBJETO;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcionObjeto;

    @Column(nullable = false)
    private double peso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    /**
     * Constructor que da valores iniciales a los atributos de clase
     *
     * @param nombre            del objeto
     * @param peso              peso del objeto
     * @param descripcionObjeto descripcion del objeto
     */
    public ObjetoInventario(String nombre, double peso, String descripcionObjeto) {
        this.nombre = nombre;
        this.peso = peso;
        this.descripcionObjeto = descripcionObjeto;
        this.categoria = Categoria.NORMAL;
    }

    public ObjetoInventario(String nombre, double peso, String descripcionObjeto, Categoria categoria) {
        this.nombre = nombre;
        this.peso = peso;
        this.descripcionObjeto = descripcionObjeto;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ObjetoInventario{" +
                "nombre='" + nombre + '\'' +
                ", descripcionObjeto='" + descripcionObjeto + '\'' +
                ", peso=" + peso +
                ", Categoria=" + categoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObjetoInventario that = (ObjetoInventario) o;
        return Double.compare(peso, that.peso) == 0 && Objects.equals(nombre, that.nombre) && Objects.equals(descripcionObjeto, that.descripcionObjeto) && categoria == that.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcionObjeto, peso, categoria);
    }
}




