package ar.edu.utn.frc.tup.lciii.modelo.partida;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public  class RecuperarDatos {
    private Tablero instaciaTablero = Tablero.ObtenerTableroVacio();

    public static String[] recuperarPiezasEnTableroGuardado(String piezasBd) {
        String[] piezas = piezasBd.split("\\|");

        return piezas;
    }

    public static Pieza asignarPieza(char denominacion, char color) {
        Pieza pieza;
        if (color == 'B') {
            switch (denominacion) {

                case '-':
                    pieza = new Pieza(EnumPieza.PEON_B);
                    break;
                case 'R':
                    pieza = new Pieza(EnumPieza.TORRE_B);
                    break;
                case 'N':
                    pieza = new Pieza(EnumPieza.CABALLO_B);
                    break;
                case 'B':
                    pieza = new Pieza(EnumPieza.ALFIL_B);
                    break;
                case 'K':
                    pieza = new Pieza(EnumPieza.REY_B);
                    break;
                case 'Q':
                    pieza = new Pieza(EnumPieza.REINA_B);
                    break;

                default:
                    pieza = null;
                    break;

            }

        } else {
            switch (denominacion) {

                case '-':
                    pieza = new Pieza(EnumPieza.PEON_N);
                    break;
                case 'R':
                    pieza = new Pieza(EnumPieza.TORRE_N);
                    break;
                case 'N':
                    pieza = new Pieza(EnumPieza.CABALLO_N);
                    break;
                case 'B':
                    pieza = new Pieza(EnumPieza.ALFIL_N);
                    break;
                case 'K':
                    pieza = new Pieza(EnumPieza.REY_N);
                    break;
                case 'Q':
                    pieza = new Pieza(EnumPieza.REINA_N);
                    break;

                default:
                    pieza = null;
                    break;

            }
        }
        return pieza;
    }



    public static List<String> recuperarAnotaciones(String anotacionesMovimientosBd) {

        String[] movimientos = anotacionesMovimientosBd.split("\\-");
        List<String> movimientoslist= new ArrayList<>();
        for(String movimiento : movimientos) {
            movimientoslist.add(movimiento);
        }
        return movimientoslist;
        }

    public static String recuperarEnumEstado(String estadoDb){
        String enumString = estadoDb;
        String[] parts = enumString.split("\\.");
        String enumType = parts[0];
        String enumConstant = parts[1];
        return enumConstant;
    }

    public static List<String> recuperarJugadores(String jugadoresBd) {
        String[] jugadores = jugadoresBd.split("\\|\\|");
        List<String> jugadoresList = new ArrayList<>();
        for (String jugador : jugadores) {
            String[] jugadorNombre = jugador.split("\\.");
            if (jugadorNombre.length > 1) {
                jugadoresList.add(jugadorNombre[1]);
            }
        }
        return jugadoresList;
    }


}





