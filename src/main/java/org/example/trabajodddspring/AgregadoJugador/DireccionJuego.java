package org.example.trabajodddspring.AgregadoJugador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "DireccionJuego",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"piso", "codigoPostal", "calle"})
        }
)
public class DireccionJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;

    @Column(nullable = false)
    private String piso;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private String codigoPostal;

    /**
     * Constructor por defecto para los atributos
     *
     * @param ciudad Ciudad a la que pertenece la dirección
     * @param calle  Calle a la que pertenece la dirección
     * @param piso   Piso a la que pertenece la dirección
     */
    public DireccionJuego(String ciudad, String calle, String piso, String codigoPostal) {
        setCiudad(ciudad);
        setCalle(calle);
        setPiso(piso);
        setCodigoPostal(codigoPostal);
    }

    /**
     * Comprueba que el código postal tenga exactamente 5 números
     *
     * @param codigoPostal Codigo postal de la dirección
     * @throws IllegalArgumentException Lanza esta excepción en caso de que el código postal no sea válido
     */
    public void setCodigoPostal(String codigoPostal) throws IllegalArgumentException {
        if (codigoPostal.length() != 5)
            throw new IllegalArgumentException("El código postal debe tener 5 dígitos");
        this.codigoPostal = codigoPostal;
    }

    // Equals con todos los atributos de clase
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DireccionJuego that = (DireccionJuego) o;
        return Objects.equals(ciudad, that.ciudad) && Objects.equals(calle, that.calle) && Objects.equals(piso, that.piso) && Objects.equals(codigoPostal, that.codigoPostal);
    }

    // Hash code con todos los atributos de clase
    @Override
    public int hashCode() {
        return Objects.hash(ciudad, calle, piso, codigoPostal);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "ciudad='" + ciudad + '\'' +
                ", calle='" + calle + '\'' +
                ", piso='" + piso + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }

}
