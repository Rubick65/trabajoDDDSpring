package org.example.trabajodddspring.AgregadoAventura;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Identificamos que tendra herencia
@Table(name = "Aventura")
@Getter
@Setter
@NoArgsConstructor
public class Aventura {

    //Dificultad a elegir
    public enum Dificultad {
        FACIL, NORMAL, DIFICIL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_AVENTURA; //Id de la aventura

    @Column(nullable = false)
    private String nombreAventura; //Nombre de la aventura

    @Column(nullable = false)
    private int duracionSesionesAprox; //Duracion aproximada de las sesiones

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificultad dificultad; //Dificultad de aventura

    /**
     * Constructor que da valores iniciales a los atributos de clase
     *
     * @param nombreAventura        nombre de aventura
     * @param duracionSesionesAprox duracion aproximada de las seiones
     * @param dificultad            dificultad de aventura
     */
    public Aventura(String nombreAventura, int duracionSesionesAprox, Dificultad dificultad) {
        setNombreAventura(nombreAventura);
        setDuracionSesionesAprox(duracionSesionesAprox);
        this.dificultad = dificultad;
    }


    /**
     * Se actualiza el nombre de la aventura
     *
     * @param nombreAventura nombre actualizado
     * @throws IllegalArgumentException en caso de estar vacio
     */
    public void setNombreAventura(String nombreAventura) throws IllegalArgumentException {
        if (nombreAventura.trim().isEmpty())
            throw new IllegalArgumentException("Nombre de aventura vacío");

        this.nombreAventura = nombreAventura;
    }


    /**
     * Se actualiza la duracion aproximada de las sesiones
     *
     * @param duracionSesionesAprox duracion aproximada
     * @throws IllegalArgumentException en caso de ser cero o negativa
     */
    public void setDuracionSesionesAprox(int duracionSesionesAprox) throws IllegalArgumentException {
        if (duracionSesionesAprox <= 0)
            throw new IllegalArgumentException("Duración aventura invalida");

        this.duracionSesionesAprox = duracionSesionesAprox;
    }


    @Override
    public String toString() {
        return "Aventura{" +
                "ID_AVENTURA=" + ID_AVENTURA +
                ", nombreAventura='" + nombreAventura + '\'' +
                ", duracionSesionesAprox=" + duracionSesionesAprox +
                ", dificultad=" + dificultad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aventura aventura = (Aventura) o;
        return duracionSesionesAprox == aventura.duracionSesionesAprox && Objects.equals(nombreAventura, aventura.nombreAventura) && dificultad == aventura.dificultad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreAventura, duracionSesionesAprox, dificultad);
    }
}
