package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;

import ar.edu.utn.frc.tup.lciii.modelo.partida.Partida;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlfilStrategyTest {

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


    @DisplayName("M.VALIDOS Alfil Blanco en A3")
    @Test
    public void TestMovimientosValidos1(){
        Casilla casillaOrigen = new Casilla(2,0, new Pieza(EnumPieza.ALFIL_B));
        int numeroElementosArray = 4;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        AlfilStrategy alfilStrategy = new AlfilStrategy();
        List<Casilla> result = alfilStrategy.movimientosValidos(casillaOrigen, Color.BLANCO);
        Assertions.assertEquals(expected.size(),result.size());
    }

    @DisplayName("M.VALIDOS Alfil Negro en A6")
    @Test
    public void TestMovimientosValidos2(){
        Casilla casillaOrigen = new Casilla(5,0, new Pieza(EnumPieza.ALFIL_N));
        int numeroElementosArray = 4;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        AlfilStrategy alfilStrategy = new AlfilStrategy();
        List<Casilla> result = alfilStrategy.movimientosValidos(casillaOrigen, Color.NEGRO);
        Assertions.assertEquals(expected.size(),result.size());
    }
    @DisplayName("Mover F2 - F3")
    @Test
    public void TestMoverPieza3(){
        PeonStrategy peonStrategy = new PeonStrategy();
        int filaOrigen = 1;
        int colOrigen = 5;
        int filaDestino = 2;
        int colDestino = 5;
        String expected = "F3";
        String result = peonStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }
    @DisplayName("Mover C2 - C4")
    @Test
    public void TestMoverPieza2(){
        PeonStrategy peonStrategy = new PeonStrategy();
        int filaOrigen = 1;
        int colOrigen = 2;
        int filaDestino = 3;
        int colDestino = 2;
        String expected = "C4";
        String result = peonStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }
    @DisplayName("Mover C1 - D2")
    @Test
    public void TestMoverPieza1(){
        PeonStrategy peonStrategy = new PeonStrategy(); // mover una pieza para luego mover el alfil
        peonStrategy.moverPieza(3,1,2,3);
        peonStrategy.moverPieza(2,1,2,2);
        peonStrategy.moverPieza(1,1,2,1);
        int filaOrigen = 0;
        int colOrigen = 3;
        int filaDestino = 1;
        int colDestino = 3;
        String expected = "";
        AlfilStrategy alfilStrategy = new AlfilStrategy();
        String result = alfilStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }


    /// ver
    @DisplayName("Anotar Jugada Comiendo")
    @Test
    public void anotarJugada() {

        Partida instancia = new Partida();
        AlfilStrategy alfilStrategy = new AlfilStrategy();
        String resultadoObtenido = alfilStrategy.anotarJugada(4, 0, 5, 0,true);
        String resultadoDeseado = "BxA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("Anotar Jugada Sin comer")
    @Test
    public void anotarJugada2() {

        Partida instancia = new Partida();
        AlfilStrategy alfilStrategy = new AlfilStrategy();
        String resultadoObtenido = alfilStrategy.anotarJugada(4, 0, 5, 0,false);
        String resultadoDeseado = "BA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("no es jaque de ataque")
    @Test
    public void esJaqueAtaque() {

        Casilla destino= new Casilla(0,5);

        Partida instancia = new Partida();
        AlfilStrategy alfiltrategy = new AlfilStrategy();

        boolean resultadoObtenido = alfiltrategy.esJaqueAtaque(destino, Color.BLANCO);
        boolean resultadoDeseado = false;
        assertEquals(resultadoDeseado, resultadoObtenido);


    }

    @DisplayName("M. OBLIGATORIOS JAQUE")
    @Test
    public void movimientosObligatoriosParaCubrirJaque1(){
        Casilla casillaAlfil = new Casilla(5,1, new Pieza(EnumPieza.ALFIL_N));
        Casilla casillaNull = new Casilla(3,1, new Pieza(null));
        Casilla casillaRey = Tablero.ObtenerTablero().encontrarCasillaRey(Color.BLANCO);
        int numeroElementosArray = 4;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        AlfilStrategy alfilStrategy = new AlfilStrategy();
        List<Casilla> result = alfilStrategy.movimientosObligatoriosParaCubrirJaque(casillaAlfil, casillaRey);
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
