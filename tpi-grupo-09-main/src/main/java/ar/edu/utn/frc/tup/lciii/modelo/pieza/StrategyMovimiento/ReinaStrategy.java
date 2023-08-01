package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;

import ar.edu.utn.frc.tup.lciii.modelo.partida.EnumEstadoJuego;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Casilla;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.utn.frc.tup.lciii.modelo.partida.Partida.estadoJuego;
import static ar.edu.utn.frc.tup.lciii.modelo.partida.Partida.lstMovimientosPermitidosHabiendoJaque;
import static ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero.*;

public class ReinaStrategy implements MovimientoStrategy {
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
                tablero[filaDestino][colDestino].SetPieza(new Pieza(EnumPieza.REINA_B));
                tablero[filaOrigen][colOrigen].SetPieza(null);
            }
            else if (colorPieza==Color.NEGRO){
                tablero[filaDestino][colDestino].SetPieza(new Pieza(EnumPieza.REINA_N));
                tablero[filaOrigen][colOrigen].SetPieza(null);
            }
            instanciaTablero.PintarTablero();
            resultado = anotarJugada(filaOrigen,colOrigen,filaDestino,colDestino,comioPieza);
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

        Casilla[][] tablero1 = ObtenerTablero().getTablero();
        List<Casilla> lstMovimientosValidos = new ArrayList<>();
        int fila = casillaOrigen.getFila();
        int col = casillaOrigen.getColumna();

        if(casillaOrigen.getPieza()!=null){
            tablero1[fila][col].SetPieza(casillaOrigen.getPieza());
        }

//                                            MAPEO EN CRUZ
        fila = casillaOrigen.getFila();
        col = casillaOrigen.getColumna();
        for (int i=fila+1; i<ObtenerTablero().getFILAS(); i++){
            if (tablero1[i][col].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(i,col));
            }
            else {
                if (tablero1[i][col].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(i,col));
                    break;
                }
                break;
            }
        }

        fila = casillaOrigen.getFila();
        col = casillaOrigen.getColumna();
        for (int i=col+1; i<ObtenerTablero().getCOLUMNAS(); i++){
            if (tablero1[fila][i].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(fila,i));
            }
            else {
                if (tablero1[fila][i].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(fila,i));
                    break;
                }
                break;
            }
        }

        fila = casillaOrigen.getFila();
        col = casillaOrigen.getColumna();
        for (int i=fila-1; i>=0; i--){
            if (tablero1[i][col].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(i,col));
            }
            else {
                if (tablero1[i][col].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(i,col));
                    break;
                }
                break;
            }
        }

        fila = casillaOrigen.getFila();
        col = casillaOrigen.getColumna();
        for (int i=col-1; i>=0; i--){
            if (tablero1[fila][i].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(fila,i));
            }
            else {
                if (tablero1[fila][i].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(fila,i));
                    break;
                }
                break;
            }
        }

//                                                  MAPEO EN EQUIS

        fila = casillaOrigen.getFila()-1;
        col = casillaOrigen.getColumna()-1;
        for (int i=0; i<8 && fila>=0 && col>=0; i++){
            if (tablero1[fila][col].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(fila,col));
            }
            else {
                if (tablero1[fila][col].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(fila,col));
                    break;
                }
                break;
            }
            fila--;
            col--;
        }

        fila = casillaOrigen.getFila()+1;
        col = casillaOrigen.getColumna()+1;
        for (int i=0; i<8 && fila<ObtenerTablero().getFILAS() && col<ObtenerTablero().getCOLUMNAS(); i++){
            if (tablero1[fila][col].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(fila,col));
            }
            else {
                if (tablero1[fila][col].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(fila,col));
                    break;
                }
                break;

            }
            fila++;
            col++;
        }

        fila = casillaOrigen.getFila()-1;
        col = casillaOrigen.getColumna()+1;
        for (int i=0; i<8 && fila>=0 && col<ObtenerTablero().getCOLUMNAS(); i++){
            if (tablero1[fila][col].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(fila,col));
            }
            else {
                if (tablero1[fila][col].getPieza()!=null && tablero1[fila][col].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(fila,col));
                    break;
                }
                break;
            }
            fila--;
            col++;
        }

        fila = casillaOrigen.getFila()+1;
        col = casillaOrigen.getColumna()-1;
        for (int i=0; i<8 && fila<ObtenerTablero().getFILAS() && col>=0; i++){
            if (tablero1[fila][col].getPieza()==null){
                lstMovimientosValidos.add(new Casilla(fila,col));
            }
            else {
                if (tablero1[fila][col].getPieza()!=null && tablero1[fila][col].getPieza().getNombrePieza().getColor()!=colorPieza){
                    lstMovimientosValidos.add(new Casilla(fila,col));
                    break;
                }
                break;
            }
            fila++;
            col--;
        }

        return lstMovimientosValidos;
    }
    @Override
    public String anotarJugada(int filaOrigen, int colOrigen, int filaDestino, int colDestino,  boolean comioPieza) {
        String cadenaResultado;

        if(comioPieza){
            cadenaResultado= EnumPieza.REINA_N.getNotacion() + "x"+ MovimientoPieza.convertirNumeroALetra(colDestino)+(filaDestino+1);
        }else{
            cadenaResultado= EnumPieza.REINA_N.getNotacion() + MovimientoPieza.convertirNumeroALetra(colDestino) + (filaDestino+1);
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
    public List<Casilla> movimientosObligatoriosParaCubrirJaque(Casilla casillaAtacante, Casilla casillaRey){

        List<Casilla> lstCasillas = new ArrayList<>();
        int filaAtacante = casillaAtacante.getFila();
        int colAtacante = casillaAtacante.getColumna();
        int filaRey = casillaRey.getFila();
        int colRey = casillaRey.getColumna();

//                                        MAPEO EQUIS

        for (int i=0; i<8 && filaAtacante>filaRey && colAtacante>colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            filaAtacante--;
            colAtacante--;
        }
        for (int i=0; i<8 && filaAtacante<filaRey && colAtacante<colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            filaAtacante++;
            colAtacante++;
        }
        for (int i=0; i<8 && filaAtacante>filaRey && colAtacante<colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            filaAtacante--;
            colAtacante++;
        }
        for (int i=0; i<8 && filaAtacante<filaRey && colAtacante>colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            filaAtacante++;
            colAtacante--;
        }
//                                              MAPEO CRUZ

        for (int i=0; i<8 && filaAtacante == filaRey && colAtacante>colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            colAtacante--;
        }
        for (int i=0; i<8 && filaAtacante == filaRey && colAtacante<colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            colAtacante++;
        }
        for (int i=0; i<8 && filaAtacante>filaRey && colAtacante == colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            filaAtacante--;
        }
        for (int i=0; i<8 && filaAtacante<filaRey && colAtacante == colRey; i++){
            lstCasillas.add(new Casilla(filaAtacante,colAtacante));
            filaAtacante++;
        }

        if (!lstCasillas.contains(casillaAtacante)){
            lstCasillas.add(casillaAtacante);
        }
        return lstCasillas;
    }
}
