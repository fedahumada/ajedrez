package ar.edu.utn.frc.tup.lciii.modelo.pieza;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento.MovimientoPieza;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PiezaTest {


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
        @DisplayName("Convertir 5 a F")
        public void convertirNumeroALetra() {

            String resultadoDeseado= "F";
            String resultadoObtenido =  MovimientoPieza.convertirNumeroALetra(5);
            assertEquals(resultadoDeseado, resultadoObtenido);
        }
    @Test
    @DisplayName("Convertir 4 a E")
    public void convertirNumeroALetra4() {

        String resultadoDeseado= "E";
        String resultadoObtenido =  MovimientoPieza.convertirNumeroALetra(4);
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @Test
    @DisplayName("Convertir 6 a G")
    public void convertirNumeroALetra5() {

        String resultadoDeseado= "G";
        String resultadoObtenido =  MovimientoPieza.convertirNumeroALetra(6);
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @Test
    @DisplayName("Convertir 7 a H")
    public void convertirNumeroALetra6() {

        String resultadoDeseado= "H";
        String resultadoObtenido =  MovimientoPieza.convertirNumeroALetra(7);
        assertEquals(resultadoDeseado, resultadoObtenido);
    }


    @Test
    @DisplayName("Convertir 9 a -")
    public void convertirNumeroALetra3() {

        String resultadoDeseado= "-";
        String resultadoObtenido =  MovimientoPieza.convertirNumeroALetra(9);
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @Test
    @DisplayName("Convertir 0 a A")
    public void convertirNumeroALetra2() {

        String resultadoDeseado= "A";
        String resultadoObtenido =  MovimientoPieza.convertirNumeroALetra(0);
        assertEquals(resultadoDeseado, resultadoObtenido);
    }



 }


