package ar.edu.utn.frc.tup.lciii.modelo.menu;

import ar.edu.utn.frc.tup.lciii.App;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
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
        @DisplayName("Imprimir reglas de juego")
        public void reglasDeJuego() throws Exception {
            Menu instancia = new Menu();

            Method metodoPrivado = Menu.class.getDeclaredMethod("reglasDeJuego");
            metodoPrivado.setAccessible(true);
            metodoPrivado.invoke(instancia);

            assertEquals("1 - El Ajedrez se juega entre dos jugadores y mueven ambos por turnos. Nunca puede un jugador hacer dos movimientos seguidos.\n" +
                              "2 - Empiezan siempre a mover en primer lugar las piezas blancas (o más claras)\n" +
                            "3 - Cuando una pieza rival amenaza comer al rey (Jaque) se debe evitar antes de hacer cualquier otro movimiento.\n" +
                            "4 - La partida termina con el Jaque Mate que es la posición en la que no es posible evitar que el rey sea comido por una pieza rival." + System.lineSeparator(), getOutput());

        }


    @Test
    @DisplayName("Saludar")
    public void saludar() throws Exception {
        Menu instancia = new Menu();
        instancia.saludar();
        String resultadoEsperado= "BIENVENIDOS AL JUEGO DE AJEDREZ DEL GRUPO 9";

        assertTrue(getOutput().contains(resultadoEsperado));

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
        ar.edu.utn.frc.tup.lciii.App.main(new String[0]);
        assertTrue(getOutput().contains(resultadoEsperado));
    }


    @Test
    @Disabled
    @DisplayName("Test Jaque Pastor2")
    public void testApp2() throws Exception {

        String testString = "1" + System.lineSeparator() +
                "fede" + System.lineSeparator() +
                "rival" + System.lineSeparator() +
                "f2 f4" + System.lineSeparator() +
                "e7 e6" + System.lineSeparator() +
                "e2 e4" + System.lineSeparator() +
                "d8 h4" + System.lineSeparator() +
                "e1 f2" + System.lineSeparator() +
                "salir" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "n" + System.lineSeparator();

        provideInput(testString);
        String resultadoEsperado = "¡TU REY ESTA EN JAQUE, DEFIÉNDELO!";

        App.main(new String[0]);
        assertTrue(getOutput().contains(resultadoEsperado));
    }
    @Test
    @DisplayName("Mostrar menú")
    public void mostrarMenu() throws Exception{
        Menu instancia = new Menu();

        String resultadoEsperado = "INGRESE EL NUMERO DE LA OPCION A ELEGIR:" + System.lineSeparator()+
                "1 - Nueva Partida" + System.lineSeparator()+
                "2 - Cargar Partida" +System.lineSeparator()+
                "3 - Reglas del juego" +System.lineSeparator()+
                "4 - Para salir";

        Method metodo = Menu.class.getDeclaredMethod("mostrarMenu");
        metodo.setAccessible(true);
        metodo.invoke(instancia);
        assertTrue(getOutput().contains(resultadoEsperado));
    }


        private void provideInput (String data){
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        private String getOutput () {
            return testOut.toString();
        }

}
