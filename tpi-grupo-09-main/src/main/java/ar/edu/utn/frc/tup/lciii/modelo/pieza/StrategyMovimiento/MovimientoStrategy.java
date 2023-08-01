package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Casilla;
import java.util.List;

public interface MovimientoStrategy {

    String moverPieza(int colOrigen, int filaOrigen, int filaDestino, int colDestino);

    List<Casilla> movimientosValidos(Casilla casillaOrigen, Color colorPieza);

    String anotarJugada(int filaOrigen, int colOrigen, int filaDestino, int colDestino, boolean comioPieza);

    boolean esJaqueAtaque(Casilla casillaDestino,Color colorPieza);

    List<Casilla> movimientosObligatoriosParaCubrirJaque(Casilla casillaAtacante, Casilla casillaRey);
}
