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

public class ReyStrategyTest {
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

    @DisplayName("M.VALIDOS Rey Blanco en B6")
    @Test
    public void TestMovimientosValidos1(){
        ReyStrategy reyStrategy = new ReyStrategy();
        Casilla casillaOrigen = new Casilla(5,1, new Pieza(EnumPieza.REY_B));
        int numeroElementosArray = 8;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = reyStrategy.movimientosValidos(casillaOrigen, Color.BLANCO);
        Assertions.assertEquals(expected.size(),result.size());
    }

    @DisplayName("M.VALIDOS Rey Negro en B3")
    @Test
    public void TestMovimientosValidos2(){
        ReyStrategy reyStrategy = new ReyStrategy();
        Casilla casillaOrigen = new Casilla(2,1, new Pieza(EnumPieza.REY_N));
        int numeroElementosArray = 8;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = reyStrategy.movimientosValidos(casillaOrigen, Color.NEGRO);
        Assertions.assertEquals(expected.size(),result.size());
    }
    @DisplayName("moverPieza") //ver
    @Test
    public void moverPieza() {

        Partida instancia = new Partida();

        ReyStrategy reyStrategy = new ReyStrategy();
        String resultadoObtenido = reyStrategy.moverPieza(4, 0, 5, 0);
        String resultadoDeseado = "";

        assertEquals(resultadoDeseado, resultadoObtenido);

    }
    @DisplayName("Anotar Jugada Comiendo")
    @Test
    public void anotarJugada() {

        Partida instancia = new Partida();
       ReyStrategy reyStrategy = new ReyStrategy();
        String resultadoObtenido = reyStrategy.anotarJugada(4, 0, 5, 0,true);
        String resultadoDeseado = "KxA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("Anotar Jugada Sin comer")
    @Test
    public void anotarJugada2() {

        Partida instancia = new Partida();
        ReyStrategy reyStrategy = new ReyStrategy();
        String resultadoObtenido = reyStrategy.anotarJugada(4, 0, 5, 0,false);
        String resultadoDeseado = "KA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("no es jaque de ataque")
    @Test
    public void esJaqueAtaque() {

        Casilla destino= new Casilla(0,5);

        Partida instancia = new Partida();
        ReyStrategy reyStrategy = new ReyStrategy();

        boolean resultadoObtenido = reyStrategy.esJaqueAtaque(destino, Color.BLANCO);
        boolean resultadoDeseado = false;
        assertEquals(resultadoDeseado, resultadoObtenido);


    }

    @DisplayName("M. OBLIGATORIOS JAQUE")
    @Test
    public void movimientosObligatoriosParaCubrirJaque1(){
        Casilla casillaNull = new Casilla(1,5, new Pieza(null));
        Casilla casillaReyN = new Casilla(1,5, new Pieza(EnumPieza.REY_N));
        Casilla casillaReyB = Tablero.ObtenerTablero().encontrarCasillaRey(Color.BLANCO);
        int numeroElementosArray = 2;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        ReyStrategy reyStrategy = new ReyStrategy();
        List<Casilla> result = reyStrategy.movimientosObligatoriosParaCubrirJaque(casillaReyN, casillaReyB);
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
