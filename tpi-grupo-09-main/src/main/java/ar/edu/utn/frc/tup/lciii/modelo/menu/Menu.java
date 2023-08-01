package ar.edu.utn.frc.tup.lciii.modelo.menu;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ar.edu.utn.frc.tup.lciii.dbManager.dbService;
import ar.edu.utn.frc.tup.lciii.modelo.jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.modelo.partida.Partida;
import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.Data;
@Data
public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private Partida partida;
    private Jugador jugador;
    private boolean recuperarPartida = true;

    public void saludar(){
          System.out.println(
                "________________________________________________________"+System.lineSeparator()+
                "\u265A\uFE0F"+"    BIENVENIDOS AL JUEGO DE AJEDREZ DEL GRUPO 9    "+"\u265A\uFE0F"+System.lineSeparator()+
                "________________________________________________________"+System.lineSeparator()
        );
    }
    @Generated //Se testean los métodos por separado.
    public void opciones() {
       mostrarMenu();
        int opcion= scanner.nextInt();
        scanner.nextLine();

        switch(opcion){
            case 1: iniciarPartida();
            break;
            case 2: recuperarPartidasGuardadas();
            break;
            case 3: MostrarDelJuego();
            case 4: return;
            default:
                System.out.println("Seleccione otra opcion");
                opciones();
                break;
        }
    }

    @Generated
    private void mostrarMenu() {
        System.out.println("INGRESE EL NUMERO DE LA OPCION A ELEGIR:");
        System.out.println("1 - Nueva Partida");
        System.out.println("2 - Cargar Partida");
        System.out.println("3 - Reglas del juego");
        System.out.println("4 - Para salir");
    }

    @Generated
    private void recuperarPartidasGuardadas() {
        List<String> partidasGuardadas = dbService.getInstance().obtenerListaPartidas();
        int option = elegirPartidasRecuperadas(partidasGuardadas);
        if (option > 0) {
            recuperarPartida = true;
            dbService.buscarPartida(option);
        } else {
            recuperarPartida = false;
            opciones();
        }
    }



    @Generated
    private int elegirPartidasRecuperadas(List<String> partidasGuardadas) {
        Scanner scanner = new Scanner(System.in);
        Integer result;
        System.out.println("Ingrese el número de la partida a cargar, o ingrese 0 (cero) para volver al menú anterior.");
        do {
            for (String p : partidasGuardadas) {
                System.out.println(p);
            }
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese el número de una de las partidas guardadas. Sino, ingrese 0 para volver al menú anterior");
                scanner.next();
            }
            result = scanner.nextInt();

            if (result < 0 || result > partidasGuardadas.size()) {
                System.out.println("Por favor, ingrese el número de una de las partidas guardadas. Sino, ingrese 0 para volver al menú anterior.");
            }
        } while (result < 0 || result > partidasGuardadas.size());

        return result;
    }

    @Generated
    private void iniciarPartida() {
        List<String> jugadores= pedirJugadores();
        partida = new Partida(jugadores.get(0), jugadores.get(1));

    }

    @Generated
    private List<String> pedirJugadores() {
       List<String> jugadores = new ArrayList<>();
        System.out.println("INGRESE NOMBRE JUGADOR PIEZAS BLANCAS " + "\u26AA");
        String auxNombre1= scanner.nextLine();
        System.out.println("INGRESE NOMBRE JUGADOR PIEZAS NEGRAS " + "\u26AB");
        String auxNombre2= scanner.nextLine();
        jugadores.add(auxNombre1);
        jugadores.add(auxNombre2);

        return jugadores;
    }

    private void reglasDeJuego() {
        System.out.println("1 - El Ajedrez se juega entre dos jugadores y mueven ambos por turnos. Nunca puede un jugador hacer dos movimientos seguidos.\n" +
                "2 - Empiezan siempre a mover en primer lugar las piezas blancas (o más claras)\n" +
                "3 - Cuando una pieza rival amenaza comer al rey (Jaque) se debe evitar antes de hacer cualquier otro movimiento.\n" +
                "4 - La partida termina con el Jaque Mate que es la posición en la que no es posible evitar que el rey sea comido por una pieza rival.");

    }

    @Generated
    private void MostrarDelJuego(){
        reglasDeJuego();
        opciones();
    }
}
