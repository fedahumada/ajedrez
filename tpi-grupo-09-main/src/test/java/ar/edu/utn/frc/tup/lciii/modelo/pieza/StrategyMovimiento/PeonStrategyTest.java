package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;

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
import java.util.ArrayList;
import java.util.List;

public class PeonStrategyTest {

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


    @DisplayName("M.VALIDOS Peon Blanco en B6")
    @Test
    public void TestMovimientosValidos1(){
        PeonStrategy peonStrategy = new PeonStrategy();
        Casilla casillaOrigen = new Casilla(5,1, new Pieza(EnumPieza.PEON_B));
        int numeroElementosArray = 2;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = peonStrategy.movimientosValidos(casillaOrigen, Color.BLANCO);
        Assertions.assertEquals(expected.size(),result.size());
    }
    @DisplayName("M.VALIDOS Peon Negro en B3")
    @Test
    public void TestMovimientosValidos2(){
        PeonStrategy peonStrategy = new PeonStrategy();
        Casilla casillaOrigen = new Casilla(2,1, new Pieza(EnumPieza.PEON_N));
        int numeroElementosArray = 2;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = peonStrategy.movimientosValidos(casillaOrigen, Color.NEGRO);
        Assertions.assertEquals(expected.size(),result.size());
    }
    @DisplayName("Mover A2 - A3")
    @Test
    public void TestMoverPieza1(){
        PeonStrategy peonStrategy = new PeonStrategy();
        int filaOrigen = 1;
        int colOrigen = 0;
        int filaDestino = 2;
        int colDestino = 0;
        String expected = "A3";
        String result = peonStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }
    @DisplayName("Mover D7 - D6")
    @Test
    public void TestMoverPieza2(){
        PeonStrategy peonStrategy = new PeonStrategy();
        int filaOrigen = 6;
        int colOrigen = 3;
        int filaDestino = 5;
        int colDestino = 3;
        String expected = "D6";
        String result = peonStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }
    @DisplayName("Mover C2 - C4")
    @Test
    public void TestMoverPieza3(){
        PeonStrategy peonStrategy = new PeonStrategy();
        int filaOrigen = 1;
        int colOrigen = 2;
        int filaDestino = 3;
        int colDestino = 2;
        String expected = "C4";
        String result = peonStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }
    @DisplayName("Mover F7 - F5")
    @Test
    public void TestMoverPieza4(){
        PeonStrategy peonStrategy = new PeonStrategy();
        int filaOrigen = 6;
        int colOrigen = 5;
        int filaDestino = 4;
        int colDestino = 5;
        String expected = "F5";
        String result = peonStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }

    @DisplayName("M. OBLIGATORIOS JAQUE")
    @Test
    public void movimientosObligatoriosParaCubrirJaque1(){
        Casilla casillaNull = new Casilla(1,5, new Pieza(null));
        Casilla casillaPeon = new Casilla(1,5, new Pieza(EnumPieza.PEON_N));
        Casilla casillaRey = Tablero.ObtenerTablero().encontrarCasillaRey(Color.BLANCO);
        int numeroElementosArray = 2;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        PeonStrategy peonStrategy = new PeonStrategy();
        List<Casilla> result = peonStrategy.movimientosObligatoriosParaCubrirJaque(casillaPeon, casillaRey);
        Assertions.assertEquals(expected.size(),result.size());
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}


