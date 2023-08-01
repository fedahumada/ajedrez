package ar.edu.utn.frc.tup.lciii.modelo.tablero;

import ar.edu.utn.frc.tup.lciii.modelo.pieza.Color;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.EnumPieza;
import ar.edu.utn.frc.tup.lciii.modelo.pieza.Pieza;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class TableroTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private Tablero tableroCompleto;
    private Tablero tableroVacio;
    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        Tablero.LimpiarTablero();
        tableroCompleto = Tablero.ObtenerTablero();
        tableroVacio = Tablero.ObtenerTableroVacio();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testObtenerTablero() {
        Tablero tablero1 = Tablero.ObtenerTablero();
        Tablero tablero2 = Tablero.ObtenerTablero();
        Assertions.assertNotNull(tablero1);
        Assertions.assertNotNull(tablero2);
        Assertions.assertSame(tablero1, tablero2);
    }

    @Test
    public void testObtenerTableroVacio() {
        Tablero tablero1 = Tablero.ObtenerTableroVacio();
        Tablero tablero2 = Tablero.ObtenerTableroVacio();
        Assertions.assertNotNull(tablero1);
        Assertions.assertNotNull(tablero2);
        Assertions.assertSame(tablero1, tablero2);
    }

    @Test
    public void testSetearPiezasGuardadas() {
        String[] piezasTablero = {"RN77", "-B33"};
        Tablero.setearPiezasGuardadas(piezasTablero);
        Casilla casilla1 = tableroVacio.getTablero()[7][7];
        Casilla casilla2 = tableroVacio.getTablero()[3][3];
        Assertions.assertEquals(EnumPieza.TORRE_N, casilla1.getPieza().getNombrePieza());
        Assertions.assertEquals(EnumPieza.PEON_B, casilla2.getPieza().getNombrePieza());
    }

    @Test
    public void testLimpiarTablero() {
        Tablero.LimpiarTablero();
        Tablero tablero = Tablero.ObtenerTablero();
        Assertions.assertNotNull(tablero);
    }

    @Test
    public void testSetColorEnemigo() {
        Color colorAliado = Color.BLANCO;
        Color colorEnemigo = tableroCompleto.setColorEnemigo(colorAliado);
        Assertions.assertEquals(Color.NEGRO, colorEnemigo);
    }

    @Test
    public void testEncontrarCasillaRey() {

        Tablero tableroMock = Mockito.mock(Tablero.class);
        Casilla casillaReyMock = new Casilla(0,4);

        Casilla casilla = tableroMock.ObtenerTablero().encontrarCasillaRey(Color.BLANCO);
        Assertions.assertNotNull(casilla);
        Assertions.assertEquals(casillaReyMock, casilla);
    }

    @Test

    public void testEncontrarCasillaPieza() {
        Pieza pieza = new Pieza(EnumPieza.ALFIL_B);

        Casilla casilla = tableroCompleto.encontrarCasillaPieza(pieza);
        Assertions.assertNotNull(casilla);
        Assertions.assertEquals(new Casilla(0,2), casilla);
    }

    @Test
    public void testRollBack() {
        tableroCompleto.getTablero()[0][2].SetPieza(null); // le mandamo

        Casilla casilla= new Casilla(0,2);
       Pieza pieza= new Pieza(EnumPieza.ALFIL_B);
        tableroCompleto.rollBack(casilla, pieza);

        Assertions.assertEquals(EnumPieza.ALFIL_B, tableroCompleto.getTablero()[0][2].getPieza().getNombrePieza());
        Assertions.assertNotSame(pieza, casilla.getPieza());
    }

    @Test
    public void testGetTablero() {
        Casilla[][] tablero = tableroCompleto.getTablero();
        Assertions.assertNotNull(tablero);
        Assertions.assertEquals(8, tablero.length);
        Assertions.assertEquals(8, tablero[0].length);
    }

    @Test
    @DisplayName("Cantidad de piezas que amenazan al rey")
    public void listarPiezasQueAmenazanAlRey() {
       List<Pieza> lst = Tablero.ObtenerTablero().listarPiezasQueAmenazanAlRey(Color.BLANCO);
       int resultadoDeseado=0;
       assertEquals(resultadoDeseado , lst.size());
   }
    @Test
    @DisplayName("Cantidad de piezas que amenazan al rey")
    public void listarPiezasQueAmenazanAlRey2() {
        List<Pieza> lst = Tablero.ObtenerTablero().listarPiezasQueAmenazanAlRey(Color.NEGRO);
        int resultadoDeseado=0;
        assertEquals(resultadoDeseado , lst.size());
    }

    @Test
    public void testHayCaballosAmenazantes() throws Exception {
        Tablero tableroMock = Mockito.mock(Tablero.class);
        Casilla casillaReyMock = Mockito.mock(Casilla.class);
        Mockito.when(tableroMock.getCasillaRey()).thenReturn(casillaReyMock);
        Mockito.when(casillaReyMock.getFila()).thenReturn(6);
        Mockito.when(casillaReyMock.getColumna()).thenReturn(0);

        Color colorPieza = Color.BLANCO;

        Class<Tablero> claseDePrueba = Tablero.class;
        Method metodoPrivado = claseDePrueba.getDeclaredMethod("hayCaballosAmenazantes", Color.class);
        metodoPrivado.setAccessible(true);
        boolean resultadoObtenido = (boolean) metodoPrivado.invoke(tableroMock, colorPieza);
        assertFalse(resultadoObtenido);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}
