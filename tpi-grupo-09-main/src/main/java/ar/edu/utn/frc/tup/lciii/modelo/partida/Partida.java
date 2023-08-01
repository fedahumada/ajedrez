package ar.edu.utn.frc.tup.lciii.modelo.partida;

import ar.edu.utn.frc.tup.lciii.modelo.jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento.*;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Casilla;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import ar.edu.utn.frc.tup.lciii.dbManager.dbService;

import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

import static ar.edu.utn.frc.tup.lciii.modelo.menu.Menu.scanner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    private static final String SI_NO_REGEX = "[sSnN]";
    private static final String JUGADA_REGEX = "^[A-Ha-h][1-8]$";
    public static List<Casilla> lstMovimientosPermitidosHabiendoJaque;
    public static EnumEstadoJuego estadoJuego = EnumEstadoJuego.OK;
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero instaciaTablero = Tablero.ObtenerTablero();
    private Casilla[][] tablero = instaciaTablero.getTablero();
    private Jugador jugadorActual;
    private int turno;
    private Boolean JuegoEnCurso;
    private Pieza piezaElegida;
    private MovimientoStrategy movimientoStrategy = new PeonStrategy();
    private MovimientoPieza movimientoPieza = new MovimientoPieza(movimientoStrategy);
    private int[] movimientoOrigen = new int[2];
    private int[] movimientoDestino = new int[2];
    private List<String> notacionPartida = new ArrayList<>();
    private LocalDateTime fechaDeGuardado;
    private Casilla casillaDestinoAVerificar;
    private Pieza piezaAVerificar;
    private Pieza atacanteJaque;
    private Casilla casillaUltimoTurno;


    public Partida(Partida partida) {
        this.jugador1 = partida.getJugador1();
        this.jugador2 = partida.getJugador2();
        this.instaciaTablero = partida.getInstaciaTablero();
        this.jugadorActual = partida.getJugadorActual();
        this.turno = partida.getTurno();
        this.JuegoEnCurso = partida.JuegoEnCurso;
        this.piezaElegida = partida.getPiezaElegida();
        this.movimientoStrategy = partida.getMovimientoStrategy();
        this.movimientoPieza = partida.getMovimientoPieza();
        this.notacionPartida = partida.getNotacionPartida();
        this.fechaDeGuardado = partida.getFechaDeGuardado();
        EmpezarAJugar();
    }

    public Partida(Jugador jugador1, Jugador jugador2){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.instaciaTablero = Tablero.ObtenerTablero();
        this.turno = 1;
        this.JuegoEnCurso = true;
        EmpezarAJugar();
    }

    public Partida(String nombre1, String nombre2) {
        this(new Jugador(nombre1, Color.BLANCO), new Jugador(nombre2, Color.NEGRO));

    }

    public Partida(String nombre1, String nombre2, List<String> anotaciones,  String estado){
        this.jugador1 = new Jugador(nombre1,Color.BLANCO);
        this.jugador2 = new Jugador(nombre2,Color.NEGRO);
        this.instaciaTablero = Tablero.ObtenerTableroVacio();
        this.turno = anotaciones.size()-1;
        this.JuegoEnCurso = true;
        Partida.estadoJuego = EnumEstadoJuego.valueOf(estado);
        this.notacionPartida = anotaciones;

        EmpezarAJugar();
    }


    private Pieza asignarPieza(char denominacion, char color) {
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

    @Generated
    private void EmpezarAJugar() {

        while (JuegoEnCurso) {

            ComprobarTurno();
            pedirJugada();
            System.out.println("Jugadas realizadas en esta partida: ");

            int rowCount = 0; // Tracks the current row count
            int notacionIndex = 0; // Tracks the current color index

            while (notacionIndex < notacionPartida.size()) {
                if (rowCount < 12) {
                    System.out.print((notacionIndex+1) +": "+ notacionPartida.get(notacionIndex) + "  \t");
                    notacionIndex++;
                    rowCount++;
                } else {
                    System.out.println(notacionPartida.get(notacionIndex));
                    notacionIndex++;
                    rowCount = 0;
                }
            }
            System.out.println();
        }

    }

    @Generated
    private void opcionesFinDeJuego(){

        JuegoEnCurso = false;
        String opcion = pedirOpcion();
        verificarOpcion(opcion);

    }

    private void verificarOpcion(String opcion){

        switch (opcion) {
            case "1":
                guardarJuego();
                break;
            case "2":
                descartarJuego();
                break;
            case "3":
                meArrepenti();
                break;
            default:
                System.out.println("Opcion invalida");
                opcionesFinDeJuego();
        }

    }

    @Generated
    private String pedirOpcion() {
        String opcion;
        System.out.println("Elegir :" + System.lineSeparator() + " 1- "+ "\uD83D\uDCBE" +" Guardar juego " + System.lineSeparator() + " 2- " + "\u2005" + "\uD83D\uDDD1" + "\u2005" + " Descartar Juego " + System.lineSeparator() + " 3- " + "\u21A9\ufe0f" + " Volver" + System.lineSeparator());
        opcion = scanner.nextLine();

        return opcion;
    }

    private void meArrepenti() {
        JuegoEnCurso = true;
        System.out.println("\uD83D\uDFE2" + " Continua el juego...");
    }

    private void descartarJuego() {
        Tablero.LimpiarTablero();
        System.out.println("\uD83D\uDFE2" + " Juego descartado.");

    }

    private void guardarJuego(){

        String auxJugadores = procesarJugadores(jugador1, jugador2);
        String auxAnotaciones = procesarAnotaciones(notacionPartida);
        String auxPiezasTablero = procesarPiezasTablero();
        java.sql.Date fechaActual = java.sql.Date.valueOf(LocalDate.now());
        dbService.getInstance().guardarPartida(fechaActual, estadoJuego, auxJugadores, auxAnotaciones,auxPiezasTablero);
        JuegoEnCurso=false;

    }

    private String procesarPiezasTablero() {
        String piezasEnTablero = "";

        for (int row = 7; row >= 0; row--) {

            for (int col = 0; col < 8; col++) {
                if (instaciaTablero.getTablero()[row][col].getPieza() != null) {
                    var notacion = String.valueOf(instaciaTablero.getTablero()[row][col].getPieza().getNombrePieza().getNotacion());
                    var color    = String.valueOf(instaciaTablero.getTablero()[row][col].getPieza().getNombrePieza().getColor().toString().charAt(0));
                    var fila     = (Integer.toString(row));
                    var colu     = (Integer.toString(col));
                    piezasEnTablero += notacion + color + fila + colu + "|";
                }
            }
        }
        System.out.println("Tablero stringifeado "+piezasEnTablero);
        return piezasEnTablero;
    }

    private String procesarAnotaciones(List<String> notacionPartida) {
        String result="";
        if(notacionPartida.size()==0) {
            result=" ";
            return result;
        }
        else{
            for (String jugada : notacionPartida ) {
                 result += jugada+"-" ;
            }
        }

        return result.substring(0, result.length() - 1);
    }

    private String procesarJugadores(Jugador jugador1, Jugador jugador2) {
       String result="";
       result +="J1."+jugador1.getNombreJugador()+"||J2."+jugador2.getNombreJugador();

        return result;
    }

    @Generated // Se verifica el input
    public Boolean pedirJugada() {

        Boolean jugadaValida = false;

        do {
            if (!JuegoEnCurso) {
                Tablero.LimpiarTablero();
                break;
            }
            String input = pedirDatos();
            Boolean verificaciones = VerificarDatos(input);

            if (verificaciones) {
                jugadaValida = true;
            }
            else if (JuegoEnCurso) {
                System.out.println("\u26A0\ufe0f" + " Error ingresando la jugada " + "\u26A0\ufe0f");
            }
            else {
                jugadaValida = true;
                System.out.println("--FIN DEL JUEGO--");
                JuegoEnCurso = false;
                break;
            }
        } while (!jugadaValida);

        return jugadaValida;
    }

    @Generated
    private boolean VerificarDatos(String input){

        if (ValidarInputJugada(input) && !controlEsJaque() && seleccionarStrategy(this.piezaElegida)) {
            String nuevaAnotacion = this.movimientoPieza.getMovimientoStrategy().moverPieza(
                    this.movimientoOrigen[0],this.movimientoOrigen[1],
                    this.movimientoDestino[1], this.movimientoDestino[0]
            );
            atacanteJaque = this.piezaElegida;
            casillaUltimoTurno = new Casilla(this.movimientoDestino[1], this.movimientoDestino[0]);
            if (!nuevaAnotacion.equals("")) {
                notacionPartida.add(nuevaAnotacion);
            }else{
                turno--;
            }
            if (estadoJuego.equals(EnumEstadoJuego.JAQUE) && esJaqueMate()) {
                JuegoEnCurso = false;
                System.out.println("\uD83C\uDF89" + " ¡" +  jugadorActual.getNombre().toUpperCase() +
                  ", JUGADOR DE PIEZAS COLOR " + jugadorActual.getColor() + ", ES EL GANADOR! " + "\uD83C\uDF89"
                );

            }
            else if (estadoJuego.equals(EnumEstadoJuego.JAQUE) && !esJaqueMate()) {
                System.out.println("\uD83D\uDD34" + " ¡EL JUGADOR DE PIEZAS COLOR " +
                        atacanteJaque.getNombrePieza().getColor() +
                        " DEJO AL REY ENEMIGO EN JAQUE! " + "\uD83D\uDD34");
            }
            return true;
        }
        return false;
    }

    @Generated
    private String pedirDatos(){
        System.out.println(" " + System.lineSeparator() + "Ingrese COLUMNA y FILA de Origen y Destino separados por un ESPACIO.(Ej: a2 a3) Ingrese 'SALIR' para opciones de partida.");

        String input = scanner.nextLine();
        if (input.toLowerCase().equals("salir")) {
            opcionesFinDeJuego();
            return "";
        }
        return input;

    }

    private void recuperarPieza(String input) {
        input = input.toLowerCase();
        this.piezaElegida = null;
        char rowChar = input.charAt(0);
        int auxRow = rowChar - 'a';

        int auxCol = Character.getNumericValue(input.charAt(1)) - 1;

        Casilla casillaSeleccionada = instaciaTablero.getTablero()[auxCol][auxRow];
        try {
            this.piezaElegida = casillaSeleccionada.getPieza();
        } catch (Exception e) {
            System.out.println("No hay pieza alguna en la posición " + input.toUpperCase());
        }
    }

    public boolean controlEsJaque() {

        piezaAVerificar = this.piezaElegida;
        casillaDestinoAVerificar = new Casilla(this.movimientoDestino[1], this.movimientoDestino[0]);

        if (estadoJuego.equals(EnumEstadoJuego.OK)) {
            if (strategiesDePiezasQueAmenazanAlRey()) {
                System.out.println("\u26A0\ufe0f" + " ¡TU REY ESTARÁ EN JAQUE SI REALIZAS ESTE MOVIMIENTO! " + "\u26A0\ufe0f");
                estadoJuego = EnumEstadoJuego.VERIFICACION;
                return true;
            }
        }
        if (estadoJuego.equals(EnumEstadoJuego.VERIFICACION)) {
            if (this.piezaElegida.equals(piezaAVerificar) && casillaUltimoTurno.equals(casillaDestinoAVerificar)) {
                System.out.println("\u26A0\ufe0f" + " ¡NO PUEDES MOVER ESTA PIEZA! TU REY ESTARÁ EN JAQUE SI REALIZAS ESTE MOVIMIENTO " + "\u26A0\ufe0f");
                return true;
            } else {
                estadoJuego = EnumEstadoJuego.OK;
                piezaAVerificar = null;
                casillaDestinoAVerificar = null;
                return false;
            }
        }
        if (estadoJuego.equals(EnumEstadoJuego.JAQUE)){
            if (!(this.piezaElegida.getNombrePieza().equals(EnumPieza.REY_B) || this.piezaElegida.getNombrePieza().equals(EnumPieza.REY_N)) &&
                lstMovimientosPermitidosHabiendoJaque.contains(casillaDestinoAVerificar)){
                estadoJuego = EnumEstadoJuego.OK;
                return false;
            }
            else if ((this.piezaElegida.getNombrePieza().equals(EnumPieza.REY_B) || this.piezaElegida.getNombrePieza().equals(EnumPieza.REY_N))
                        && reyPuedeMoverseEnJaque()){
                estadoJuego = EnumEstadoJuego.OK;
                return false;
            }
            else {
                System.out.println("\uD83D\uDD34"  + " ¡TU REY ESTA EN JAQUE, DEFIÉNDELO! " + "\uD83D\uDD34" );
                return true;
            }
        }
        return false;
    }

    private boolean esJaqueMate(){

        Color colorEnemigo = instaciaTablero.setColorEnemigo(jugadorActual.getColor());
        this.movimientoPieza.setMovimientoStrategy(new ReyStrategy());
        List<Casilla> lstMovimientosRey = this.movimientoPieza.getMovimientoStrategy()
                .movimientosValidos(instaciaTablero.encontrarCasillaRey(colorEnemigo),colorEnemigo);
        Color colorAliado = instaciaTablero.setColorEnemigo(atacanteJaque.getNombrePieza().getColor());
        HashMap<Pieza,Casilla> lstPiezas = instaciaTablero.listarTodasPiezasContrarias(colorAliado);
        for (Map.Entry<Pieza,Casilla> elemento : lstPiezas.entrySet()) {
            Pieza pieza = elemento.getKey();
            Casilla casilla = elemento.getValue();
            List<Casilla> lst = listarCasillasDevueltasPorStrategy(pieza, casilla);
            for (Casilla lstElemento : lst) {
                if (lstMovimientosPermitidosHabiendoJaque.contains(lstElemento)){
                    return false;
                }
            }
        }

        if (lstMovimientosRey.contains(casillaUltimoTurno)){
            return false;
        }

        if (this.piezaElegida.getNombrePieza().equals(EnumPieza.CABALLO_B) ||
                this.piezaElegida.getNombrePieza().equals(EnumPieza.CABALLO_N) &&
                        lstMovimientosRey.isEmpty()){
            return true;
        }

        lstMovimientosRey.removeAll(lstMovimientosPermitidosHabiendoJaque);
        if (lstMovimientosRey.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    @Generated
    private List<Casilla> lstMovPermitidosReyRollback(){
        instaciaTablero.encontrarCasillaRey(jugadorActual.getColor()).SetPieza(null);
        if (casillaDestinoAVerificar.getPieza()==null){
            casillaDestinoAVerificar.SetPieza(this.piezaElegida);
        }
        List<Casilla> lst = listarCasillasDevueltasPorStrategy(atacanteJaque, casillaUltimoTurno);
        tablero[this.movimientoOrigen[1]][this.movimientoOrigen[0]].SetPieza(this.piezaElegida);
        casillaDestinoAVerificar.SetPieza(null);

        return lst;
    }

    @Generated
    private boolean reyPuedeMoverseEnJaque(){

        Color colorRey = this.piezaElegida.getNombrePieza().getColor();
        this.movimientoPieza.setMovimientoStrategy(new ReyStrategy());
        List<Casilla> lstMovimientosRey = this.movimientoPieza.getMovimientoStrategy()
                .movimientosValidos(instaciaTablero.encontrarCasillaRey(colorRey),colorRey);
        lstMovimientosPermitidosHabiendoJaque.addAll(lstMovPermitidosReyRollback());
        lstMovimientosRey.removeAll(lstMovimientosPermitidosHabiendoJaque);
        lstMovimientosRey.add(casillaUltimoTurno);
        if (lstMovimientosRey.contains(casillaDestinoAVerificar)){
            return true;
        }
        return false;
    }

    @Generated
    private List<Casilla> listarCasillasDevueltasPorStrategy(Pieza pieza, Casilla casilla) {

         List<Casilla> lst = new ArrayList<>();
        if (seleccionarStrategy(pieza)){
             return this.movimientoPieza.getMovimientoStrategy().movimientosValidos(
                     casilla,pieza.getNombrePieza().getColor());
         }
         return lst;
    }

    @Generated
    public boolean strategiesDePiezasQueAmenazanAlRey() {

        if (this.piezaElegida.getNombrePieza().equals(EnumPieza.REY_N) || this.piezaElegida.getNombrePieza().equals(EnumPieza.REY_B)){
            instaciaTablero.setCasillaRey(casillaDestinoAVerificar);
            if (instaciaTablero.hayCaballosAmenazantes(this.piezaElegida.getNombrePieza().getColor())){
                return true;
            }
        }
        tablero[this.movimientoOrigen[1]][this.movimientoOrigen[0]].SetPieza(null);
        List<Pieza> listarPiezasQueAmenazanAlRey = instaciaTablero.listarPiezasQueAmenazanAlRey(jugadorActual.getColor());
        instaciaTablero.rollBack(new Casilla(this.movimientoOrigen[1], this.movimientoOrigen[0]), this.piezaElegida);
        for (Pieza pieza : listarPiezasQueAmenazanAlRey) {
            if (seleccionarStrategyPiezasEnemigas(pieza)) {
                return true;
            }
        }
        return false;
    }

    private boolean seleccionarStrategyPiezasEnemigas(Pieza pieza) {

        Boolean esJaque = false;
        Casilla casilla = instaciaTablero.encontrarCasillaPieza(pieza);
        Color colorEnemigo = instaciaTablero.setColorEnemigo(this.piezaElegida.getNombrePieza().getColor());
        tablero[this.movimientoOrigen[1]][this.movimientoOrigen[0]].SetPieza(null);
        switch (pieza.getNombrePieza()){
            case PEON_B :
            case PEON_N :
                this.movimientoPieza.setMovimientoStrategy(new PeonStrategy());
                if (this.movimientoPieza.getMovimientoStrategy().esJaqueAtaque(casilla,colorEnemigo)){
                    esJaque = true;
                }
                break;
            case TORRE_B :
            case TORRE_N :
                this.movimientoPieza.setMovimientoStrategy(new TorreStrategy());
                if (this.movimientoPieza.getMovimientoStrategy().esJaqueAtaque(casilla,colorEnemigo)){
                    esJaque = true;
                }
                break;
            case CABALLO_B :
            case CABALLO_N :
                this.movimientoPieza.setMovimientoStrategy(new CaballoStrategy());
                if (this.movimientoPieza.getMovimientoStrategy().esJaqueAtaque(casilla,colorEnemigo)){
                    esJaque = true;
                }
                break;
            case REINA_B :
            case REINA_N :
                this.movimientoPieza.setMovimientoStrategy(new ReinaStrategy());
                if (this.movimientoPieza.getMovimientoStrategy().esJaqueAtaque(casilla,colorEnemigo)){
                    esJaque = true;
                }
                break;
            case REY_B :
            case REY_N :
                this.movimientoPieza.setMovimientoStrategy(new ReyStrategy());
                if (this.movimientoPieza.getMovimientoStrategy().esJaqueAtaque(casilla,colorEnemigo)){
                    esJaque = true;
                }
                break;
            case ALFIL_B :
            case ALFIL_N :
                this.movimientoPieza.setMovimientoStrategy(new AlfilStrategy());
                if (this.movimientoPieza.getMovimientoStrategy().esJaqueAtaque(casilla,colorEnemigo)){
                    esJaque = true;
                }
                break;

            default:
                esJaque = null;
        }
        instaciaTablero.rollBack(new Casilla(this.movimientoOrigen[1],this.movimientoOrigen[0]),this.piezaElegida);
        return esJaque;

    }

    private boolean seleccionarStrategy(Pieza piezaSeleccionada){

        boolean auxRespuesta = false;

        switch (piezaSeleccionada.getNombrePieza()){
            case PEON_B :
            case PEON_N :
                this.movimientoPieza.setMovimientoStrategy(new PeonStrategy());
                auxRespuesta = true;
                break;
            case TORRE_B :
            case TORRE_N :
                this.movimientoPieza.setMovimientoStrategy(new TorreStrategy());
                auxRespuesta = true;
                break;
            case CABALLO_B :
            case CABALLO_N :
                this.movimientoPieza.setMovimientoStrategy(new CaballoStrategy());
                auxRespuesta = true;
                break;
            case REINA_B :
            case REINA_N :
                this.movimientoPieza.setMovimientoStrategy(new ReinaStrategy());
                auxRespuesta = true;
                break;
            case REY_B :
            case REY_N :
                this.movimientoPieza.setMovimientoStrategy(new ReyStrategy());
                auxRespuesta = true;
                break;
            case ALFIL_B :
            case ALFIL_N :
                this.movimientoPieza.setMovimientoStrategy(new AlfilStrategy());
                auxRespuesta = true;
                break;

            default:
                System.out.println("Debe seleccionar una de sus piezas");
                pedirJugada();
                break;
        }

        return auxRespuesta;
    }

    private boolean ValidarInputJugada(String input) {

        Pattern pattern = Pattern.compile(JUGADA_REGEX);
        boolean result = false;
        this.piezaElegida = null;
        if (!(input.trim().length()==5)){
            return false;
        }
        String[] positions = input.trim().split("\\s+");


        if (pattern.matcher(positions[1].toLowerCase()).matches() &&
                pattern.matcher(positions[0].toLowerCase()).matches()) {

            String origen = positions[0].toLowerCase();
            String destino = positions[1].toLowerCase();

            int origenRow = origen.charAt(0) - 'a';
            int origenCol = Integer.parseInt(origen.substring(1)) - 1 ;

            int destinoRow = destino.charAt(0) - 'a';
            int destinoCol = Integer.parseInt(destino.substring(1)) - 1;

            this.movimientoOrigen[0]=origenRow;
            this.movimientoOrigen[1]=origenCol;

            this.movimientoDestino[0]=destinoRow;
            this.movimientoDestino[1]=destinoCol;

            this.recuperarPieza(origen);
            if (this.piezaElegida != null) {

                if (this.jugadorActual.getColor() == this.piezaElegida.getNombrePieza().getColor()) {
                    result = true;
                }
                else {
                    System.out.println("No puedes elegir una pieza del contrincante, tu color es " + this.jugadorActual.getColor());
                }
            }
        }

        return result;
    }

    @Generated
    public static Boolean repetirJuego() {

        Boolean respuesta = null;
        do {
            System.out.println("Quiere jugar otra partida? Responda: S / N");
            String input = scanner.nextLine();
            respuesta = getRespuestaSiNo(input);

        } while (respuesta == null);
        return respuesta;
    }

    public static Boolean getRespuestaSiNo(String input) {

        Pattern pattern = Pattern.compile(SI_NO_REGEX);
        Boolean result;
        if (pattern.matcher(input.toLowerCase()).matches() && input.equalsIgnoreCase("s")) {
            result = true;
        } else if (pattern.matcher(input.toLowerCase()).matches() && input.equalsIgnoreCase("n")) {
            result = false;
        }
        else {
            System.out.println("Por favor, ingrese una respuesta");
            result = null;
        }
        return result;
    }

    private void ComprobarTurno() {
        if (turno % 2 != 0) {
            this.jugadorActual = jugador1;
            System.out.println("Turno del jugador 1");
            turno++;
        }
        else {
            this.jugadorActual = jugador2;
            System.out.println("Turno del jugador 2");
            turno++;
        }
    }
}
