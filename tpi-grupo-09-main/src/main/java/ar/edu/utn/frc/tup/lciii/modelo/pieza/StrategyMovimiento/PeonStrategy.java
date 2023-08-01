package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;
import static ar.edu.utn.frc.tup.lciii.modelo.partida.Partida.estadoJuego;
import static ar.edu.utn.frc.tup.lciii.modelo.partida.Partida.lstMovimientosPermitidosHabiendoJaque;
import static ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero.*;

import ar.edu.utn.frc.tup.lciii.modelo.partida.EnumEstadoJuego;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Casilla;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class PeonStrategy implements MovimientoStrategy {
    @Override
    public String moverPieza(int colOrigen, int filaOrigen, int filaDestino, int colDestino) {

        String resultado;
        boolean comioPieza = false;
        Tablero instanciaTablero = Tablero.ObtenerTablero();
        Casilla[][] tablero = instanciaTablero.getTablero();
        Casilla casillaOrigen = new Casilla(filaOrigen,colOrigen);
        Casilla casillaDestino = new Casilla(filaDestino,colDestino);
        Color colorPieza = tablero[filaOrigen][colOrigen].getPieza().getNombrePieza().getColor();
        List<Casilla> lstMovimientosPermitidos = movimientosValidos(casillaOrigen,colorPieza);

        if (estadoJuego.equals(EnumEstadoJuego.VERIFICACION)){
            return "";
        }

        if (lstMovimientosPermitidos.contains(casillaDestino)){
            if (tablero[filaDestino][colDestino].getPieza() != null){
                comioPieza = true;
                tablero[filaDestino][colDestino].SetPieza(null);
            }
            if (colorPieza==Color.BLANCO){
                tablero[filaDestino][colDestino].SetPieza(new Pieza(EnumPieza.PEON_B));
                tablero[filaOrigen][colOrigen].SetPieza(null);
            }
            else if (colorPieza==Color.NEGRO){
                tablero[filaDestino][colDestino].SetPieza(new Pieza(EnumPieza.PEON_N));
                tablero[filaOrigen][colOrigen].SetPieza(null);
            }
            instanciaTablero.PintarTablero();
            resultado = anotarJugada(filaOrigen+1,colOrigen,filaDestino,colDestino,comioPieza);
        }
        else {
            System.out.println("Movimiento no valido");
            resultado = "";
        }

        if (esJaqueAtaque(casillaDestino, colorPieza)){
            estadoJuego = EnumEstadoJuego.JAQUE;
            Color colorEnemigo = instanciaTablero.setColorEnemigo(colorPieza);
            lstMovimientosPermitidosHabiendoJaque = movimientosObligatoriosParaCubrirJaque(
                    casillaDestino,instanciaTablero.encontrarCasillaRey(colorEnemigo));
        }

        return resultado;
    }
    @Override
    public List<Casilla> movimientosValidos(Casilla casillaOrigen, Color colorPieza) {
        List<Casilla> lstMovimientosValidos = new ArrayList<>();
        Casilla[][] tablero = ObtenerTablero().getTablero();
        int fila = casillaOrigen.getFila();
        int col = casillaOrigen.getColumna();

        if (casillaOrigen.getPieza() != null) {
            tablero[fila][col].SetPieza(casillaOrigen.getPieza());
        }

        if (colorPieza == Color.BLANCO) {
            fila = casillaOrigen.getFila() + 1;
            col = casillaOrigen.getColumna();
            if (fila < ObtenerTablero().getFILAS() && tablero[fila][col].getPieza() == null) {
                lstMovimientosValidos.add(new Casilla(fila, col));
            }

            fila = casillaOrigen.getFila() + 2;
            col = casillaOrigen.getColumna();
            if (fila <  ObtenerTablero().getFILAS() && tablero[fila][col].getPieza() == null &&
                    casillaOrigen.getFila() == 1) {
                lstMovimientosValidos.add(new Casilla(fila, col));
            }

            fila = casillaOrigen.getFila() + 1;
            col = casillaOrigen.getColumna() + 1;
            if ((fila <  ObtenerTablero().getFILAS() && col <  ObtenerTablero().getCOLUMNAS()) && (tablero[fila][col].getPieza() != null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor() != colorPieza)) {
                lstMovimientosValidos.add(new Casilla(fila, col));
            }

            fila = casillaOrigen.getFila() + 1;
            if(casillaOrigen.getColumna() > 0){
                col = casillaOrigen.getColumna() - 1;
            }

            if ((fila <  ObtenerTablero().getFILAS() && col >= 0) && (tablero[fila][col].getPieza() != null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor() != colorPieza)) {
                lstMovimientosValidos.add(new Casilla(fila, col));
            }
        } else {
            fila = casillaOrigen.getFila() - 1;
            col = casillaOrigen.getColumna();
            if (fila >= 0 && tablero[fila][col].getPieza() == null) {
                lstMovimientosValidos.add(new Casilla(fila, col));
            }

            fila = casillaOrigen.getFila() - 2;
            col = casillaOrigen.getColumna();
            if (fila >= 0 && tablero[fila][col].getPieza() == null &&
                    casillaOrigen.getFila() == 6) {
                lstMovimientosValidos.add(new Casilla(fila, col));
            }

            fila = casillaOrigen.getFila() - 1;
            col = casillaOrigen.getColumna() + 1;
            if ((fila >= 0 && col <  ObtenerTablero().getCOLUMNAS()) && (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor()!=colorPieza))
            {
                lstMovimientosValidos.add(new Casilla(fila,col));
            }

            fila = casillaOrigen.getFila() - 1;
            if(casillaOrigen.getColumna() > 0){
                col = casillaOrigen.getColumna() -1 ;
            }
            if ((fila >= 0 && col <  ObtenerTablero().getCOLUMNAS()) && (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor()!=colorPieza))
            {
                lstMovimientosValidos.add(new Casilla(fila,col));
            }
        }

        return lstMovimientosValidos;
    }
    @Override
    public String anotarJugada(int filaOrigen, int colOrigen, int filaDestino, int colDestino,  boolean comioPieza) {

        String cadenaResultado;

        if(comioPieza){
            cadenaResultado= MovimientoPieza.convertirNumeroALetra(colOrigen)+filaOrigen+"x"+ MovimientoPieza.convertirNumeroALetra(colDestino)+(filaDestino+1);
        }else{
            cadenaResultado= MovimientoPieza.convertirNumeroALetra(colDestino) + (filaDestino+1);
        }

        return cadenaResultado;
    }
    @Override
    public boolean esJaqueAtaque(Casilla casillaDestino,Color colorPieza){
        boolean respuesta = false;
        Color colorEnemigo;
        if (colorPieza.equals(Color.BLANCO)){
            colorEnemigo = Color.NEGRO;
        }
        else {
            colorEnemigo = Color.BLANCO;
        }
        Tablero instanciaTablero = Tablero.ObtenerTablero();
        List<Casilla> lstValidarJaqueAtaque = movimientosValidos(casillaDestino, colorPieza);
        if (lstValidarJaqueAtaque.contains(instanciaTablero.encontrarCasillaRey(colorEnemigo))){
            respuesta = true;
        }
        return respuesta;
    }
    @Override
    public List<Casilla> movimientosObligatoriosParaCubrirJaque(Casilla casillaAtacante, Casilla casillaRey) {

        List<Casilla> lstCasillas = new ArrayList<>();
        int filaAtacante = casillaAtacante.getFila();
        int colAtacante = casillaAtacante.getColumna();
        int filaRey = casillaRey.getFila();
        int colRey = casillaRey.getColumna();

//                                        MAPEO EQUIS

        for (int i = 0; i < 8 && filaAtacante > filaRey && colAtacante > colRey; i++) {
            lstCasillas.add(new Casilla(filaAtacante, colAtacante));
            filaAtacante--;
            colAtacante--;
        }
        for (int i = 0; i < 8 && filaAtacante < filaRey && colAtacante < colRey; i++) {
            lstCasillas.add(new Casilla(filaAtacante, colAtacante));
            filaAtacante++;
            colAtacante++;
        }
        for (int i = 0; i < 8 && filaAtacante > filaRey && colAtacante < colRey; i++) {
            lstCasillas.add(new Casilla(filaAtacante, colAtacante));
            filaAtacante--;
            colAtacante++;
        }
        for (int i = 0; i < 8 && filaAtacante < filaRey && colAtacante > colRey; i++) {
            lstCasillas.add(new Casilla(filaAtacante, colAtacante));
            filaAtacante++;
            colAtacante--;
        }
        if (!lstCasillas.contains(casillaAtacante)){
            lstCasillas.add(casillaAtacante);
        }
        return lstCasillas;
    }
}
