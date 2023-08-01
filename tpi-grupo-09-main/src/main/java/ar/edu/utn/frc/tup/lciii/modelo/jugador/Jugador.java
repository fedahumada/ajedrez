package ar.edu.utn.frc.tup.lciii.modelo.jugador;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.Data;

@Data
@Generated
public class Jugador {
    private String nombre;
    private Color color;

    public String getNombreJugador() {
        return nombre;
    }

    public void setNombreJugador(String nombre) {
        this.nombre = nombre;
    }

    public Color getColorJugador() {
        return color;
    }

    public void setColorJugador(Color color) {
        this.color = color;
    }

    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

}
