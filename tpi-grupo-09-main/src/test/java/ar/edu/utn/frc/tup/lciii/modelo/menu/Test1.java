package ar.edu.utn.frc.tup.lciii.modelo.menu;

import ar.edu.utn.frc.tup.lciii.App;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Test1 {
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
    @Disabled
    @DisplayName("Test Jaque Pastor")
    public void testApp1() throws Exception {

        String testString = "1" + System.lineSeparator() +
                "fede" + System.lineSeparator() +
                "rival" + System.lineSeparator() +
                "e7 e6" + System.lineSeparator() +
                "salir" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "n" + System.lineSeparator();

        provideInput(testString);
        String resultadoEsperado = "No puedes elegir una pieza del contrincante, tu color es BLANCO";
        App.main(new String[0]);
        Assertions.assertTrue(getOutput().contains(resultadoEsperado));
    }



    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}


