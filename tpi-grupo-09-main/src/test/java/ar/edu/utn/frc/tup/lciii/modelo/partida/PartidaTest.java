package ar.edu.utn.frc.tup.lciii.modelo.partida;

import ar.edu.utn.frc.tup.lciii.modelo.jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartidaTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;


    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        Tablero.LimpiarTablero();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }



    @Test
    @DisplayName("Seleccionar TORRE_B")
    public void seleccionarStrategy() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza(EnumPieza.TORRE_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(true ,resultadoObtenido);
    }
    @Test
    @DisplayName("Seleccionar CABALLO_B")
    public void seleccionarStrategy2() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza(EnumPieza.CABALLO_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(true ,resultadoObtenido);
    }
    @Test
    @DisplayName("Seleccionar ALFIL_B")
    public void seleccionarStrategy3() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza(EnumPieza.ALFIL_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(true ,resultadoObtenido);
    }
    @Test
    @DisplayName("Seleccionar REY_B")
    public void seleccionarStrategy4() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza(EnumPieza.REY_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(true ,resultadoObtenido);
    }
    @Test
    @DisplayName("Seleccionar REINA_B")
    public void seleccionarStrategy5() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza(EnumPieza.REINA_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(true ,resultadoObtenido);
    }
    @Test
    @DisplayName("Seleccionar PEON_B")
    public void seleccionarStrategy6() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza(EnumPieza.PEON_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(true ,resultadoObtenido);
    }


    @Test
    @Disabled  // no se puede selleccionar pieza incorrecta ya que solo se asignan piezas por el enum.
    @DisplayName("Seleccionar pieza INCORRECTA")
    public void seleccionarStrategy13() throws Exception {

        Partida instancia = new Partida();
        Pieza Seleccionada= new Pieza();
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategy", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);


    }

    @Test
    @DisplayName("seleccionarStrategyPiezasEnemigas PEON_B")
    public void seleccionarStrategyPiezasEnemigas() throws Exception {
        Tablero.LimpiarTablero();
        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("Daniel",Color.BLANCO));
        instancia.setPiezaElegida(new Pieza(EnumPieza.PEON_B));

        Pieza Seleccionada= new Pieza(EnumPieza.PEON_N);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategyPiezasEnemigas", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);
    }
    @Test
    @DisplayName("seleccionarStrategyPiezasEnemigas CABALLO_B")
    public void seleccionarStrategyPiezasEnemigas2() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("Daniel",Color.NEGRO));
        instancia.setPiezaElegida(new Pieza(EnumPieza.PEON_N));


        Pieza Seleccionada= new Pieza(EnumPieza.CABALLO_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategyPiezasEnemigas", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);
    }
    @Test
    @DisplayName("seleccionarStrategyPiezasEnemigas ALFIL_B")
    public void seleccionarStrategyPiezasEnemigas3() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("Daniel",Color.NEGRO));
        instancia.setPiezaElegida(new Pieza(EnumPieza.PEON_N));

        Pieza Seleccionada= new Pieza(EnumPieza.ALFIL_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategyPiezasEnemigas", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);
    }

    @Test
    @DisplayName("seleccionarStrategyPiezasEnemigas REY_B")
    public void seleccionarStrategyPiezasEnemigas4() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("Daniel",Color.NEGRO));
        instancia.setPiezaElegida(new Pieza(EnumPieza.PEON_N));

        Pieza Seleccionada= new Pieza(EnumPieza.REY_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategyPiezasEnemigas", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);
    }
    @Test
    @DisplayName("seleccionarStrategyPiezasEnemigas REINA_B")
    public void seleccionarStrategyPiezasEnemigas5() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("Daniel",Color.NEGRO));
        instancia.setPiezaElegida(new Pieza(EnumPieza.PEON_N));

        Pieza Seleccionada= new Pieza(EnumPieza.REINA_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategyPiezasEnemigas", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);
    }
    @Test
    @DisplayName("seleccionarStrategyPiezasEnemigas TORRE_B")
    public void seleccionarStrategyPiezasEnemigas6() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("Daniel",Color.NEGRO));
        instancia.setPiezaElegida(new Pieza(EnumPieza.PEON_N));

        Pieza Seleccionada= new Pieza(EnumPieza.TORRE_B);
        Method metodoPrivado = Partida.class.getDeclaredMethod("seleccionarStrategyPiezasEnemigas", Pieza.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, Seleccionada);

        assertEquals(false ,resultadoObtenido);
    }


    @Test
    @DisplayName("Respuesta SI")
    public void getRespuestaSiNo() throws Exception {

        Partida instancia = new Partida();
        String input="s";

        Method metodoPrivado = Partida.class.getDeclaredMethod("getRespuestaSiNo", String.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, input);
        Boolean resultadoEsperado= true;

        assertEquals(resultadoEsperado ,resultadoObtenido);
    }
    @Test
    @DisplayName("Respuesta No")
    public void getRespuestaSiNo2() throws Exception {

        Partida instancia = new Partida();
        String input="n";

        Method metodoPrivado = Partida.class.getDeclaredMethod("getRespuestaSiNo", String.class);
        metodoPrivado.setAccessible(true);
        Boolean resultadoObtenido= (boolean)metodoPrivado.invoke(instancia, input);
        Boolean resultadoEsperado= false;

        assertEquals(resultadoEsperado ,resultadoObtenido);
    }


    @Test
    @DisplayName("Respuesta Juegan Blancas")
    public void comprobarTurnos() throws Exception {

        Partida instancia = new Partida();
        instancia.setTurno(3);

        Method metodoPrivado = Partida.class.getDeclaredMethod("ComprobarTurno");
        metodoPrivado.setAccessible(true);
       metodoPrivado.invoke(instancia);
        assertTrue(getOutput().contains("Turno del jugador 1"));
    }

    @Test
    @DisplayName("Turno del jugador 1")
    public void comprobarTurnos2() throws Exception {

        Partida instancia = new Partida();
        instancia.setTurno(3);

        Method metodoPrivado = Partida.class.getDeclaredMethod("ComprobarTurno");
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia);

        assertTrue(getOutput().contains("Turno del jugador 1"));

    }


    @Test
    @Disabled
    @DisplayName("Datos ok")
    public void VerificarDatos() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("daniel", Color.BLANCO));
        String input="a2 a3";

        Method metodoPrivado = Partida.class.getDeclaredMethod("VerificarDatos", String.class);
        metodoPrivado.setAccessible(true);
        Boolean resultado= (boolean)metodoPrivado.invoke(instancia, input);

        Boolean resultadoEsperado = true;
        assertEquals(resultadoEsperado ,resultado);
    }

    @Test
    @DisplayName("trata de mover pieza negra")
    public void VerificarDatos2() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugadorActual(new Jugador("daniel", Color.BLANCO));
        String input="a7 a6";

        Method metodoPrivado = Partida.class.getDeclaredMethod("VerificarDatos", String.class);
        metodoPrivado.setAccessible(true);
        Boolean resultado= (boolean)metodoPrivado.invoke(instancia, input);

        Boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado ,resultado);
    }



    @Test
    @DisplayName("Elejir pieza Torrre_B")
    public void RecuperarPieza() throws Exception {

        Partida instancia = new Partida();
        String input="h1";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.TORRE_B;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }

    @Test
    @DisplayName("Elejir pieza Torrre_N")
    public void RecuperarPieza2() throws Exception {

        Partida instancia = new Partida();
        String input="h8";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.TORRE_N;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }

    @Test
    @DisplayName("Elejir pieza CABALLO_B")
    public void RecuperarPieza3() throws Exception {

        Partida instancia = new Partida();
        String input="b1";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.CABALLO_B;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }
    @Test
    @DisplayName("Elejir pieza REINA_B")
    public void RecuperarPieza4() throws Exception {

        Partida instancia = new Partida();
        String input="d1";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.REINA_B;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }
    @Test
    @DisplayName("Elejir pieza REY_B")
    public void RecuperarPieza5() throws Exception {

        Partida instancia = new Partida();
        String input="e1";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.REY_B;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }
    @Test
    @DisplayName("Elejir pieza ALFIL_B")
    public void RecuperarPieza6() throws Exception {

        Partida instancia = new Partida();
        String input="c1";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.ALFIL_B;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }
    @Test
    @DisplayName("Elejir pieza PEON_B")
    public void RecuperarPieza7() throws Exception {

        Partida instancia = new Partida();
        String input="c2";

        Method metodoPrivado = Partida.class.getDeclaredMethod("recuperarPieza", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, input);

        EnumPieza resultadoEsperado= EnumPieza.PEON_B;
        assertEquals(resultadoEsperado ,instancia.getPiezaElegida().getNombrePieza());
    }


    @Test
    @Disabled
    @DisplayName("Ingresar jugada valida")
    public void pedirJugada() throws Exception {

        Partida instancia = new Partida();
        instancia.setJugador1(new Jugador("Daniel",Color.BLANCO));
        instancia.setJugador2(new Jugador("Fede",Color.NEGRO));


        instancia.setJugadorActual(instancia.getJugador1());

        String input="a2 a3";

        Method metodoPrivado = Partida.class.getDeclaredMethod("VerificarDatos", String.class);
        metodoPrivado.setAccessible(true);

        boolean resultadoObtenido= (boolean) metodoPrivado.invoke(instancia, input);
        boolean resultadoEsperado= true;

        assertEquals(resultadoEsperado, resultadoObtenido);
    }



    @Test
    @DisplayName("Opcion salida 2")
    public void verificarOpcion2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String opcion = "2";
        Partida instancia = new Partida();

        Method metodoPrivado = Partida.class.getDeclaredMethod("verificarOpcion", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, opcion);

        String resultadoEsperado = "Juego descartado";

        assertTrue(getOutput().contains(resultadoEsperado)); // cuando secrea la clase partida imprime el tablero, por eso usamos el contains y no el equals
    }
    @Test
    @DisplayName("Opcion salida 3")
    public void verificarOpcion3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String opcion = "3";
        Partida instancia = new Partida();
        instancia.setJuegoEnCurso(false);

        Method metodoPrivado = Partida.class.getDeclaredMethod("verificarOpcion", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, opcion);

        boolean resultadoEsperado = true;
        boolean resultadoObtenido=(boolean)instancia.getJuegoEnCurso();

        assertEquals(resultadoEsperado , resultadoObtenido); // Aca se seteo el valor en false y el metodo lo cambiaba
    }


    @Test
    @DisplayName("procesarJugadores")
    public void procesarJugadores() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida instancia= new Partida();
        Jugador j1= new Jugador("Daniel", Color.BLANCO);
        Jugador j2= new Jugador("El Rey", Color.NEGRO);

        Method metodoPrivado = Partida.class.getDeclaredMethod("procesarJugadores", Jugador.class, Jugador.class);
        metodoPrivado.setAccessible(true);
        String resultadoObtenido = (String) metodoPrivado.invoke(instancia, j1,j2);

        String resultadoEsperado = "J1.Daniel||J2.El Rey";

        assertEquals(resultadoEsperado , resultadoObtenido);
    }

    @Test
    @DisplayName("procesarAnotaciones")
    public void procesarAnotaciones() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida instancia= new Partida();
       List<String> jugadas= new ArrayList<String>();
       jugadas.add("-N73");
        jugadas.add("BN75");

        Method metodoPrivado = Partida.class.getDeclaredMethod("procesarAnotaciones", List.class);
        metodoPrivado.setAccessible(true);
        String resultadoObtenido = (String) metodoPrivado.invoke(instancia,jugadas);

        String resultadoEsperado = "-N73-BN75";

        assertEquals(resultadoEsperado , resultadoObtenido);
    }



    @Test
    @DisplayName("Asignar piezas")
    public void asignarPieza() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida instancia= new Partida();

        char blanco ='B';
        char negro ='N';

        char peon ='-' ;
        char alfil ='B' ;
        char torre = 'R' ;
        char rey= 'K' ;
        char reina='Q' ;
        char  caballo='N' ;
        char error = 'H';


        Method metodoPrivado = Partida.class.getDeclaredMethod("asignarPieza", char.class, char.class);
        metodoPrivado.setAccessible(true);

        Pieza resultadoObtenido = (Pieza) metodoPrivado.invoke(instancia, peon, blanco);
        Pieza resultadoObtenido2 = (Pieza) metodoPrivado.invoke(instancia, alfil,blanco);
        Pieza resultadoObtenido3 = (Pieza) metodoPrivado.invoke(instancia, torre,blanco);
        Pieza resultadoObtenido4 = (Pieza) metodoPrivado.invoke(instancia, rey, blanco);
        Pieza resultadoObtenido5 = (Pieza) metodoPrivado.invoke(instancia, reina,blanco);
        Pieza resultadoObtenido6 = (Pieza) metodoPrivado.invoke(instancia, caballo, blanco);
        Pieza resultadoObtenido7 = (Pieza) metodoPrivado.invoke(instancia, error, blanco);

        Pieza resultadoObtenido8 = (Pieza) metodoPrivado.invoke(instancia, peon, negro);
        Pieza resultadoObtenido9 = (Pieza) metodoPrivado.invoke(instancia, alfil,negro);
        Pieza resultadoObtenido10 = (Pieza) metodoPrivado.invoke(instancia, torre,negro);
        Pieza resultadoObtenido11 = (Pieza) metodoPrivado.invoke(instancia, rey, negro);
        Pieza resultadoObtenido12 = (Pieza) metodoPrivado.invoke(instancia, reina,negro);
        Pieza resultadoObtenido13 = (Pieza) metodoPrivado.invoke(instancia, caballo, negro);
        Pieza resultadoObtenido14 = (Pieza) metodoPrivado.invoke(instancia, error, negro);

        Pieza resultadoEsperado = new Pieza(EnumPieza.PEON_B);
        Pieza resultadoEsperado2 = new Pieza(EnumPieza.ALFIL_B);
        Pieza resultadoEsperado3 = new Pieza(EnumPieza.TORRE_B);
        Pieza resultadoEsperado4 = new Pieza(EnumPieza.REY_B);
        Pieza resultadoEsperado5 = new Pieza(EnumPieza.REINA_B);
        Pieza resultadoEsperado6 = new Pieza(EnumPieza.CABALLO_B);
        Pieza resultadoEsperado7 = null;

        Pieza resultadoEsperado8 = new Pieza(EnumPieza.PEON_N);
        Pieza resultadoEsperado9 = new Pieza(EnumPieza.ALFIL_N);
        Pieza resultadoEsperado10 = new Pieza(EnumPieza.TORRE_N);
        Pieza resultadoEsperado11 = new Pieza(EnumPieza.REY_N);
        Pieza resultadoEsperado12 = new Pieza(EnumPieza.REINA_N);
        Pieza resultadoEsperado13 = new Pieza(EnumPieza.CABALLO_N);
        Pieza resultadoEsperado14 = null;


        assertEquals(resultadoEsperado, resultadoObtenido);
        assertEquals(resultadoEsperado2, resultadoObtenido2);
        assertEquals(resultadoEsperado3, resultadoObtenido3);
        assertEquals(resultadoEsperado4, resultadoObtenido4);
        assertEquals(resultadoEsperado5, resultadoObtenido5);
        assertEquals(resultadoEsperado6, resultadoObtenido6);
        assertEquals(resultadoEsperado7, resultadoObtenido7);
        assertEquals(resultadoEsperado8, resultadoObtenido8);
        assertEquals(resultadoEsperado9, resultadoObtenido9);
        assertEquals(resultadoEsperado10, resultadoObtenido10);
        assertEquals(resultadoEsperado11, resultadoObtenido11);
        assertEquals(resultadoEsperado12, resultadoObtenido12);
        assertEquals(resultadoEsperado13, resultadoObtenido13);
        assertEquals(resultadoEsperado14, resultadoObtenido14);
    }


    @Test
    @DisplayName("Opcion salida 1")
    public void verificarOpcion1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida instancia = new Partida();
        instancia.setJugador1(new Jugador("Daniel",Color.BLANCO));
        instancia.setJugador2(new Jugador("Fede",Color.NEGRO));

        Method metodoPrivado = Partida.class.getDeclaredMethod("verificarOpcion", String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, "1");

        Boolean resultadoObtenido=  instancia.getJuegoEnCurso();
        Boolean resultadoEsperado=false;

        assertEquals(resultadoEsperado, resultadoObtenido);

    }
        private void provideInput (String data){
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput () {
        return testOut.toString();
    }


}
