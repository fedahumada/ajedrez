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

public class CaballoStrategyTest {

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


    @DisplayName("M.VALIDOS Caballo Blanco en D4")
    @Test
    public void TestMovimientosValidos1(){
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        Casilla casillaOrigen = new Casilla(3,3, new Pieza(EnumPieza.CABALLO_B));
        int numeroElementosArray = 6;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = caballoStrategy.movimientosValidos(casillaOrigen, Color.BLANCO);
        Assertions.assertEquals(expected.size(),result.size());
    }

    @DisplayName("M.VALIDOS Caballo Negro en D3")
    @Test
    public void TestMovimientosValidos2(){
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        Casilla casillaOrigen = new Casilla(2,3, new Pieza(EnumPieza.CABALLO_N));
        int numeroElementosArray = 8;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = caballoStrategy.movimientosValidos(casillaOrigen, Color.NEGRO);
        Assertions.assertEquals(expected.size(),result.size());
    }
    @DisplayName("Mover B1 - A2 // no valido")
    @Test
    public void TestMoverPieza1(){
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        int filaOrigen = 0;
        int colOrigen = 1;
        int filaDestino = 1;//2
        int colDestino = 0;
        String expected = "";
        String result = caballoStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }
    @DisplayName("Mover B1 - A3")
    @Test
    public void TestMoverPieza2(){
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        int filaOrigen = 0;
        int colOrigen = 1;
        int filaDestino = 2;
        int colDestino = 0;
        String expected = "NA3";
        String result = caballoStrategy.moverPieza(colOrigen,filaOrigen,filaDestino,colDestino);
        Assertions.assertEquals(expected,result);
    }


    /// ver
    @DisplayName("Anotar Jugada Comiendo")
    @Test
    public void anotarJugada() {

        Partida instancia = new Partida();
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        String resultadoObtenido = caballoStrategy.anotarJugada(4, 0, 5, 0,true);
        String resultadoDeseado = "NxA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("Anotar Jugada Sin comer")
    @Test
    public void anotarJugada2() {

        Partida instancia = new Partida();
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        String resultadoObtenido = caballoStrategy.anotarJugada(4, 0, 5, 0,false);
        String resultadoDeseado = "NA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("no es jaque de ataque")
    @Test
    public void esJaqueAtaque() {

        Casilla destino= new Casilla(0,5);

        Partida instancia = new Partida();
        CaballoStrategy caballoStrategy = new CaballoStrategy();

        boolean resultadoObtenido = caballoStrategy.esJaqueAtaque(destino, Color.BLANCO);
        boolean resultadoDeseado = false;
        assertEquals(resultadoDeseado, resultadoObtenido);


    }

    @DisplayName("M. OBLIGATORIOS JAQUE")
    @Test
    public void movimientosObligatoriosParaCubrirJaque1(){
        Casilla casillaCaballo = new Casilla(2,5, new Pieza(EnumPieza.CABALLO_N));
        Casilla casillaRey = Tablero.ObtenerTablero().encontrarCasillaRey(Color.BLANCO);
        int numeroElementosArray = 1;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        CaballoStrategy caballoStrategy = new CaballoStrategy();
        List<Casilla> result = caballoStrategy.movimientosObligatoriosParaCubrirJaque(casillaCaballo, casillaRey);
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
