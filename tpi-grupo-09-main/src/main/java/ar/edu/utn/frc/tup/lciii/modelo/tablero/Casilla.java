package ar.edu.utn.frc.tup.lciii.modelo.tablero;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import lombok.Data;

@Data
public class Casilla {
    private Integer fila;
    private Integer columna;
    private Color color;
    private Pieza pieza;

    public Casilla(Integer fila, Integer columna, Color color) {
        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }
    public Casilla(Integer fila, Integer columna, Pieza pieza) {
        this.fila = fila;
        this.columna = columna;
        this.pieza = pieza;
    }
    public Casilla(Integer fila, Integer columna) {
        this.fila = fila;
        this.columna = columna;
    }
    public Casilla() {
    }
    public void SetPieza(Pieza pieza) {
        this.pieza = pieza;
    }
}
