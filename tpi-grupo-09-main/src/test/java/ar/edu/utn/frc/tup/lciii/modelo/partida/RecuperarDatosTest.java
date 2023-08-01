package ar.edu.utn.frc.tup.lciii.modelo.partida;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Casilla;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecuperarDatosTest {

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
    @DisplayName("Recuperar piezas")
    public void testRepetirJuego() {
        String piezasString = "RN70|BN72|QN73|KN74|QB03|KB04" ;
        String piezasString2 = "RN70|BN72|QN73|BN75|NN76|RN77|-N60|-N61|-N62|-N63|-N64|-N65|-N66|-N67|NN52|-B33|-B10|-B11|-B12|-B14|-B15|-B16|-B17|RB00|NB01|BB02|QB03|KB04|BB05|NB06|RB07|KN74|QB03|KB04" ;

        String result= Arrays.toString(RecuperarDatos.recuperarPiezasEnTableroGuardado(piezasString));
        String result2= Arrays.toString(RecuperarDatos.recuperarPiezasEnTableroGuardado(piezasString2));
        assertEquals("[RN70, BN72, QN73, KN74, QB03, KB04]", result);
        assertEquals("[RN70, BN72, QN73, BN75, NN76, RN77, -N60, -N61, -N62, -N63, -N64, -N65, -N66, -N67, NN52, -B33, -B10, -B11, -B12, -B14, -B15, -B16, -B17, RB00, NB01, BB02, QB03, KB04, BB05, NB06, RB07, KN74, QB03, KB04]", result2);
        }

        @Test
        @DisplayName("Asignar piezas Negras")
        public void asignarPieza() {
            Pieza result = RecuperarDatos.asignarPieza('K', 'N');
            Pieza result2 = RecuperarDatos.asignarPieza('Q', 'N');
            Pieza result3 = RecuperarDatos.asignarPieza('B', 'N');
            Pieza result4 = RecuperarDatos.asignarPieza('N', 'N');
            Pieza result5 = RecuperarDatos.asignarPieza('-', 'N');
            Pieza result6 = RecuperarDatos.asignarPieza('R', 'N');
            Pieza result7 = RecuperarDatos.asignarPieza('H', 'N');

            Pieza resultadoEsperado = new Pieza(EnumPieza.REY_N);
            Pieza resultadoEsperado2 = new Pieza(EnumPieza.REINA_N);
            Pieza resultadoEsperado3 = new Pieza(EnumPieza.ALFIL_N);
            Pieza resultadoEsperado4 = new Pieza(EnumPieza.CABALLO_N);
            Pieza resultadoEsperado5 = new Pieza(EnumPieza.PEON_N);
            Pieza resultadoEsperado6 = new Pieza(EnumPieza.TORRE_N);
            Pieza resultadoEsperado7 = null;

            assertEquals(resultadoEsperado, result);
            assertEquals(resultadoEsperado2, result2);
            assertEquals(resultadoEsperado3, result3);
            assertEquals(resultadoEsperado4, result4);
            assertEquals(resultadoEsperado5, result5);
            assertEquals(resultadoEsperado6, result6);
            assertEquals(resultadoEsperado7, result7);
        }

    @Test
    @DisplayName("Asignar piezas Blancas")
    public void asignarPieza2() {

        Pieza result = RecuperarDatos.asignarPieza('K', 'B');
        Pieza result2 = RecuperarDatos.asignarPieza('Q', 'B');
        Pieza result3 = RecuperarDatos.asignarPieza('B', 'B');
        Pieza result4 = RecuperarDatos.asignarPieza('N', 'B');
        Pieza result5 = RecuperarDatos.asignarPieza('-', 'B');
        Pieza result6 = RecuperarDatos.asignarPieza('R', 'B');
        Pieza result7 = RecuperarDatos.asignarPieza('H', 'B');

        Pieza resultadoEsperado = new Pieza(EnumPieza.REY_B);
        Pieza resultadoEsperado2 = new Pieza(EnumPieza.REINA_B);
        Pieza resultadoEsperado3 = new Pieza(EnumPieza.ALFIL_B);
        Pieza resultadoEsperado4 = new Pieza(EnumPieza.CABALLO_B);
        Pieza resultadoEsperado5 = new Pieza(EnumPieza.PEON_B);
        Pieza resultadoEsperado6 = new Pieza(EnumPieza.TORRE_B);
        Pieza resultadoEsperado7 = null;
        assertEquals(resultadoEsperado, result);
        assertEquals(resultadoEsperado2, result2);
        assertEquals(resultadoEsperado3, result3);
        assertEquals(resultadoEsperado4, result4);
        assertEquals(resultadoEsperado5, result5);
        assertEquals(resultadoEsperado6, result6);
        assertEquals(resultadoEsperado7, result7);
    }

//recuperarAnotaciones
    @Test
    @DisplayName("Recuperar Anotaciones")
    public void recuperarAnotaciones() {
        String piezasString = "RN70-BN72-QN73-KN74-QB03-KB04" ;
        String piezasString2 = "QN73-BN75-NN76-RN77-NN52-QB03-KB04-BB05-NB06-KB04" ;

        List<String> result= RecuperarDatos.recuperarAnotaciones(piezasString);
        List<String> movimientosEsperados = Arrays.asList("RN70", "BN72", "QN73","KN74", "QB03", "KB04");
       List<String> result2=  RecuperarDatos.recuperarAnotaciones(piezasString2);
        List<String> movimientosEsperados2 = Arrays.asList("QN73", "BN75", "NN76","RN77", "NN52", "QB03","KB04", "BB05", "NB06","KB04");

        assertEquals(movimientosEsperados, result);
        assertEquals(movimientosEsperados2, result2);

    }

    @Test
    @DisplayName("Recuperar Anotaciones")
    public void recuperarEnumEstado(){
        String enumString = EnumEstadoJuego.OK.toString();
        String resultado= RecuperarDatos.recuperarEnumEstado(enumString);
        String resultadoEsperado= "OK";
        assertEquals(resultado, resultadoEsperado);

    }


    @Test
    @DisplayName("Recuperar Jugadores")
    public void recuperarJugadores(){
        String enumString = "J1.Daniel||J2.Lucas";
        List<String> jugadores = RecuperarDatos.recuperarJugadores(enumString);
        List<String> jugadoresEsperados = Arrays.asList("Daniel", "Lucas");
        assertEquals(jugadoresEsperados, jugadores);


    }

    private void provideInput (String data){
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput () {
        return testOut.toString();
    }

}
