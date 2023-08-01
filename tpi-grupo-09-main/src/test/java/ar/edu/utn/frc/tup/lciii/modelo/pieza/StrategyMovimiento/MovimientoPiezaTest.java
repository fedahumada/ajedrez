package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;

import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


public class MovimientoPiezaTest {

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

    @DisplayName("convertirNumeroALetra")
    @Test
    public void convertirNumeroALetra(){
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(0),"A");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(1),"B");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(2),"C");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(3),"D");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(4),"E");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(5),"F");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(6),"G");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(7),"H");
        Assertions.assertEquals(MovimientoPieza.convertirNumeroALetra(8),"-");

    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}
