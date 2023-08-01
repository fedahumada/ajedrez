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


public class ReinaStrategyTest {

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

    @DisplayName("M.VALIDOS Reina Blanca en A3")
    @Test
    public void TestMovimientosValidos1(){
        ReinaStrategy reinaStrategy = new ReinaStrategy();
        Casilla casillaOrigen = new Casilla(2,0, new Pieza(EnumPieza.REINA_B));
        int numeroElementosArray = 15;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = reinaStrategy.movimientosValidos(casillaOrigen, Color.BLANCO);
        assertEquals(expected.size(),result.size());
    }

    @DisplayName("M.VALIDOS Reina Negra en A6")
    @Test
    public void TestMovimientosValidos2(){
        ReinaStrategy reinaStrategy = new ReinaStrategy();
        Casilla casillaOrigen = new Casilla(5,0, new Pieza(EnumPieza.REINA_N));
        int numeroElementosArray = 15;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = reinaStrategy.movimientosValidos(casillaOrigen, Color.NEGRO);
        assertEquals(expected.size(),result.size());
    }

    @DisplayName("M.VALIDOS Reina Negra en E8") //ver
    @Test
    public void TestMovimientosValidos3(){
        ReinaStrategy reinaStrategy = new ReinaStrategy();
        Casilla casillaOrigen = new Casilla(7,4, new Pieza(EnumPieza.REINA_N));
        int numeroElementosArray = 0;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        List<Casilla> result = reinaStrategy.movimientosValidos(casillaOrigen, Color.NEGRO);
        assertEquals(expected.size(),result.size());
    }

    @DisplayName("moverPieza") //ver
    @Test
    public void moverPieza() {

        Partida instancia = new Partida();

        ReinaStrategy reinaStrategy = new ReinaStrategy();
        String resultadoObtenido = reinaStrategy.moverPieza(4, 0, 5, 0);
        String resultadoDeseado = "";

        assertEquals(resultadoDeseado, resultadoObtenido);

    }
    @DisplayName("Anotar Jugada Comiendo")
    @Test
    public void anotarJugada() {

        Partida instancia = new Partida();
        ReinaStrategy reinaStrategy = new ReinaStrategy();
        String resultadoObtenido = reinaStrategy.anotarJugada(4, 0, 5, 0,true);
        String resultadoDeseado = "QxA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("Anotar Jugada Sin comer")
    @Test
    public void anotarJugada2() {

        Partida instancia = new Partida();
        ReinaStrategy reinaStrategy = new ReinaStrategy();
        String resultadoObtenido = reinaStrategy.anotarJugada(4, 0, 5, 0,false);
        String resultadoDeseado = "QA6";
        assertEquals(resultadoDeseado, resultadoObtenido);
    }

    @DisplayName("no es jaque de ataque")
    @Test
    public void esJaqueAtaque() {

        Casilla destino= new Casilla(0,5);

        Partida instancia = new Partida();
        ReinaStrategy reinaStrategy = new ReinaStrategy();

        boolean resultadoObtenido = reinaStrategy.esJaqueAtaque(destino, Color.BLANCO);
        boolean resultadoDeseado = false;
        assertEquals(resultadoDeseado, resultadoObtenido);

    }

    @DisplayName("M. OBLIGATORIOS JAQUE")
    @Test
    public void movimientosObligatoriosParaCubrirJaque1(){
        Casilla casillaReina = new Casilla(4,4, new Pieza(EnumPieza.REINA_N));
        Casilla casillaNull = new Casilla(1,4, new Pieza(null));
        Casilla casillaRey = Tablero.ObtenerTablero().encontrarCasillaRey(Color.BLANCO);
        int numeroElementosArray = 5;
        List<Casilla> expected = new ArrayList<>();
        for (int i=0;i<numeroElementosArray;i++){
            expected.add(new Casilla());
        }
        ReinaStrategy reinaStrategy = new ReinaStrategy();
        List<Casilla> result = reinaStrategy.movimientosObligatoriosParaCubrirJaque(casillaReina, casillaRey);
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


