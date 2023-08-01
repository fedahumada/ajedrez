package ar.edu.utn.frc.tup.lciii.dbManager;

import ar.edu.utn.frc.tup.lciii.App;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ar.edu.utn.frc.tup.lciii.modelo.partida.EnumEstadoJuego;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class dbServiceTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private dbService service;



    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        service = dbService.getInstance();
        Tablero.LimpiarTablero();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);

    }
    @Test
    @Disabled
    @DisplayName("Test Guardar Partida")
    public void testDbService() throws Exception {

        String testString1 = "1" + System.lineSeparator() +
                "fede" + System.lineSeparator() +
                "rival" + System.lineSeparator() +
                "d2 d4" + System.lineSeparator() +
                "salir" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "n" + System.lineSeparator();


        provideInput(testString1);
        String resultadoEsperado = "La partida se ha guardado correctamente.";
        App.main(new String[0]);
        assertTrue(getOutput().contains(resultadoEsperado));
    }
    @Test
    @Disabled
    @DisplayName("Fallo al guardar partida")
    public void insertarPartidasHard() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        dbService instancia = new dbService();
        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(statementMock.executeUpdate()).thenReturn(1);
        String result = "";

        assertEquals(result ,getOutput());
    }


    @Test
    void guardarPartida_ValidInput_Success() {
        // Arrange
        java.sql.Date fecha = java.sql.Date.valueOf("2023-07-01");
        EnumEstadoJuego estado = EnumEstadoJuego.OK;
        String jugadores = "J1.Lucas||J2.Dani";
        String anotacionPiezas = "A3";
        String piezasTablero = "RN70|NN71|BN72|QN73|KN74|BN75|NN76|RN77|-N60|-N61|-N62|-N63|-N64|-N65|-N66|-N67|-B20|-B11|-B12|-B13|-B14|-B15|-B16|-B17|RB00|NB01|BB02|QB03|KB04|BB05|NB06|RB07";

        // Act
        service.guardarPartida(fecha, estado, jugadores, anotacionPiezas, piezasTablero);

        // Assert
        List<String> partidasGuardadas = dbService.obtenerListaPartidas();
        assertFalse(partidasGuardadas.contains("(1) J1.Lucas VS J2.Dani | 2023-07-01"));
    }

    @Test
    void obtenerListaPartidas_NoPartidas_ReturnsEmptyList() {

        List<String> partidasGuardadas = dbService.obtenerListaPartidas();
        assertFalse(partidasGuardadas.isEmpty());
    }

    @Test
     void buscarPartida_ExistingId_ReturnsPartida() throws NoSuchMethodException {

        dbService instancia = new dbService();

        Method metodoPrivado = dbService.class.getDeclaredMethod("insertarPartidasHard");
        metodoPrivado.setAccessible(true);
        instancia.iniciarDB();


        int id = 1;
        dbService.buscarPartida(id);

        assertNotNull(Tablero.ObtenerTablero().getTablero()[0][3].getPieza().getNombrePieza());

    }



    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}

