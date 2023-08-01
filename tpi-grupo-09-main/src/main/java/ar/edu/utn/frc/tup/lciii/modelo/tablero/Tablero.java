package ar.edu.utn.frc.tup.lciii.modelo.tablero;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ar.edu.utn.frc.tup.lciii.modelo.partida.RecuperarDatos.asignarPieza;

@Data
public class Tablero {

    private Integer COLUMNAS = 8;
    private Integer FILAS = 8;
    private Casilla[][] tablero;
    private static Tablero instancia;
    private Casilla casillaRey;

    private Tablero(String modo){
        IniciarCasillas();
        if(modo.length()>0) {
            UbicarPiezas();
            PintarTablero();
        }
    }
    public static Tablero ObtenerTablero(){
        if(instancia == null) {
            instancia = new Tablero("Completo");
        }
        return instancia;
    }
    public static Tablero ObtenerTableroVacio(){
        if(instancia == null) {
            instancia = new Tablero("");
        }
        return instancia;
    }
    public static void setearPiezasGuardadas(String[] piezasTablero) {
        for (String pieza : piezasTablero) {
            // ejemplo RN77
            char denominacion = pieza.charAt(0);
            char color = pieza.charAt(1);

            int col = Integer.parseInt(pieza.substring(2,3));
            int fila = Integer.parseInt(pieza.substring(3,4));

            Pieza auxPieza = asignarPieza(denominacion, color);
            Tablero.ObtenerTableroVacio().getTablero()[col][fila].SetPieza(auxPieza);

        }
        Tablero.instancia.PintarTablero();
    }
    public static void LimpiarTablero() {
        instancia = null;
    }
    private void IniciarCasillas() {
        tablero = new Casilla[FILAS][COLUMNAS];
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Color color = (fila + columna) % 2 == 0 ? Color.NEGRO : Color.BLANCO;
                tablero[fila][columna] = new Casilla(fila,columna,color);
            }
        }
        System.out.println(); // dejamos una linea para separar
    }
    private void UbicarPiezas() {
        tablero[0][0].SetPieza(new Pieza(EnumPieza.TORRE_B));
        tablero[0][1].SetPieza(new Pieza(EnumPieza.CABALLO_B));
        tablero[0][2].SetPieza(new Pieza(EnumPieza.ALFIL_B));
        tablero[0][3].SetPieza(new Pieza(EnumPieza.REINA_B));
        tablero[0][4].SetPieza(new Pieza(EnumPieza.REY_B));
        tablero[0][5].SetPieza(new Pieza(EnumPieza.ALFIL_B));
        tablero[0][6].SetPieza(new Pieza(EnumPieza.CABALLO_B));
        tablero[0][7].SetPieza(new Pieza(EnumPieza.TORRE_B));

        for (int i = 0; i < 8; i++){
            tablero[1][i].SetPieza(new Pieza(EnumPieza.PEON_B));
            tablero[6][i].SetPieza(new Pieza(EnumPieza.PEON_N));
        }

        tablero[7][0].SetPieza(new Pieza(EnumPieza.TORRE_N));
        tablero[7][1].SetPieza(new Pieza(EnumPieza.CABALLO_N));
        tablero[7][2].SetPieza(new Pieza(EnumPieza.ALFIL_N));
        tablero[7][3].SetPieza(new Pieza(EnumPieza.REINA_N));
        tablero[7][4].SetPieza(new Pieza(EnumPieza.REY_N));
        tablero[7][5].SetPieza(new Pieza(EnumPieza.ALFIL_N));
        tablero[7][6].SetPieza(new Pieza(EnumPieza.CABALLO_N));
        tablero[7][7].SetPieza(new Pieza(EnumPieza.TORRE_N));
    }
    public void PintarTablero() {

        simularFlush();
        String tableroPintado = "";

        for (int row = 7; row >= 0; row--) {
            tableroPintado += "          "+(row + 1) + "  ";

            for (int col = 0; col < 8; col++) {
                if (tablero[row][col].getPieza() != null) {
                    String nombrePieza = tablero[row][col].getPieza().getNombrePieza().getS();
                    tableroPintado += String.format("%-3s", nombrePieza);
                } else {
                    if ((row + col) % 2 == 0) {
                        tableroPintado +="███ ";
                    } else {
                        tableroPintado +="   ";
                    }
                }
            }

            tableroPintado += System.lineSeparator();
        }

        tableroPintado += "              A   B  C  D   E  F  G   H"; // arreglar

        System.out.println(tableroPintado);
        System.out.println();
        simularFlush();
    }
    private void simularFlush() {
        for (int i = 0; i <3;i++)
        {
            System.out.println();
        }
    }
    public Color setColorEnemigo(Color colorAliado){
        Color colorEnemigo;
        if (colorAliado.equals(Color.BLANCO)){
            colorEnemigo = Color.NEGRO;
        }
        else {
            colorEnemigo = Color.BLANCO;
        }
        return colorEnemigo;
    }
    public Casilla encontrarCasillaRey(Color colorReyBuscado) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (tablero[fila][columna] != null && tablero[fila][columna].getPieza() != null) {
                    if (tablero[fila][columna].getPieza().getNombrePieza().equals(EnumPieza.REY_B) &&
                            colorReyBuscado.equals(Color.BLANCO)){
                        return new Casilla(fila,columna);
                    }
                    if (tablero[fila][columna].getPieza().getNombrePieza().equals(EnumPieza.REY_N) &&
                            colorReyBuscado.equals(Color.NEGRO)) {
                        return new Casilla(fila,columna);
                    }
                }
            }
        }

        return null;
    }
    public Casilla encontrarCasillaPieza(Pieza pieza) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (tablero[fila][columna] != null && tablero[fila][columna].getPieza() != null) {
                    if (tablero[fila][columna].getPieza().equals(pieza)){
                        return new Casilla(fila,columna);
                    }
                }
            }
        }

        return null;
    }
    public void rollBack(Casilla casillaRollBack, Pieza piezaRollBack){
        int fila = casillaRollBack.getFila();
        int col = casillaRollBack.getColumna();
        tablero[fila][col].SetPieza(piezaRollBack);
    }
    public List<Pieza> listarPiezasQueAmenazanAlRey(Color colorPieza){

        Casilla casillaDelRey = encontrarCasillaRey(colorPieza);
        List<Pieza> lstPiezas = new ArrayList<>();
        if (casillaDelRey == null){
            casillaDelRey = this.casillaRey;
        }
        int fila = casillaDelRey.getFila();
        int col = casillaDelRey.getColumna();
        Color colorEnemigo = setColorEnemigo(colorPieza);

        //                                            MAPEO EN CRUZ

        for (int i=fila+1; i<FILAS; i++){
            if (tablero[i][col].getPieza()!=null &&
                    tablero[i][col].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[i][col].getPieza()!=null &&
                    tablero[i][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[i][col].getPieza());
                break;
            }
        }
        for (int i=col+1; i<COLUMNAS; i++){
            if (tablero[fila][i].getPieza()!=null &&
                    tablero[fila][i].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[fila][i].getPieza()!=null &&
                    tablero[fila][i].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[fila][i].getPieza());
                break;
            }
        }
        for (int i=fila-1; i>=0; i--){
            if (tablero[i][col].getPieza()!=null &&
                    tablero[i][col].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[i][col].getPieza()!=null &&
                    tablero[i][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[i][col].getPieza());
                break;
            }
        }
        for (int i=col-1; i>=0; i--){
            if (tablero[fila][i].getPieza()!=null &&
                    tablero[fila][i].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[fila][i].getPieza()!=null &&
                    tablero[fila][i].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[fila][i].getPieza());
                break;
            }
        }

//                                                  MAPEO EN EQUIS

        fila = casillaDelRey.getFila()-1;
        col = casillaDelRey.getColumna()-1;
        for (int i=0; i<8 && fila>=0 && col>=0; i++){
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[fila][col].getPieza());
                break;
            }
            fila--;
            col--;
        }
        fila = casillaDelRey.getFila()+1;
        col = casillaDelRey.getColumna()+1;
        for (int i=0; i<8 && fila<FILAS && col<COLUMNAS; i++){
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[fila][col].getPieza());
                break;
            }
            fila++;
            col++;
        }
        fila = casillaDelRey.getFila()-1;
        col = casillaDelRey.getColumna()+1;
        for (int i=0; i<8 && fila>=0 && col<COLUMNAS; i++){
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[fila][col].getPieza());
                break;
            }
            fila--;
            col++;
        }
        fila = casillaDelRey.getFila()+1;
        col = casillaDelRey.getColumna()-1;
        for (int i=0; i<8 && fila<FILAS && col>=0; i++){
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorPieza)){
                break;
            }
            if (tablero[fila][col].getPieza()!=null &&
                    tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo)){
                lstPiezas.add(tablero[fila][col].getPieza());
                break;
            }
            fila++;
            col--;
        }

        return lstPiezas;
    }
    @Generated
    public boolean hayCaballosAmenazantes(Color colorPieza){

        Color colorEnemigo = setColorEnemigo(colorPieza);
        int fila;
        int col;

//                                          MAPEO VERTICAL
        fila = casillaRey.getFila()+2;
        col = casillaRey.getColumna()+1;
        if ((fila < FILAS && col < COLUMNAS) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                        tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        fila = casillaRey.getFila()+2;
        col = casillaRey.getColumna()-1;
        if ((fila < FILAS && col >= 0) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        fila = casillaRey.getFila()-2;
        col = casillaRey.getColumna()+1;
        if ((fila >= 0 && col < COLUMNAS) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        fila = casillaRey.getFila()-2;
        col = casillaRey.getColumna()-1;
        if ((fila >= 0 && col >= 0) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

//                                            MAPEO HORIZONTAL

        fila = casillaRey.getFila()+1;
        col = casillaRey.getColumna()+2;
        if ((fila<FILAS && col<COLUMNAS) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        fila = casillaRey.getFila()-1;
        col = casillaRey.getColumna()+2;
        if ((fila>=0 && col<COLUMNAS) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        fila = casillaRey.getFila()-1;
        col = casillaRey.getColumna()-2;
        if ((fila >= 0 && col >= 0) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        fila = casillaRey.getFila()+1;
        col = casillaRey.getColumna()-2;
        if ((fila<FILAS && col>=0) && tablero[fila][col].getPieza() != null &&
                (tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_N) ||
                tablero[fila][col].getPieza().getNombrePieza().equals(EnumPieza.CABALLO_B)) &&
                tablero[fila][col].getPieza().getNombrePieza().getColor().equals(colorEnemigo))
        {
            return true;
        }

        return false;
    }
    @Generated
    public HashMap<Pieza,Casilla> listarTodasPiezasContrarias(Color colorPieza) {

        HashMap<Pieza, Casilla> lstPiezas = new HashMap<>();
        Color colorEnemigo = setColorEnemigo(colorPieza);

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                if (tablero[fila][columna].getPieza() != null &&
                    !(tablero[fila][columna].getPieza().getNombrePieza().equals(EnumPieza.REY_B) ||
                      tablero[fila][columna].getPieza().getNombrePieza().equals(EnumPieza.REY_N)) &&
                      tablero[fila][columna].getPieza().getNombrePieza().getColor().equals(colorEnemigo)) {

                    lstPiezas.put(tablero[fila][columna].getPieza(), new Casilla(fila, columna));
                }
            }
        }
        return lstPiezas;
    }
    }
