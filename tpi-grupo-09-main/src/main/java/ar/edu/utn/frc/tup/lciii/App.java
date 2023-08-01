package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.modelo.menu.Menu;
import ar.edu.utn.frc.tup.lciii.modelo.partida.Partida;

public class App 
{


    public static void main( String[] args ) {
        Menu menu= new Menu();


        Boolean jugarDeNuevo;
        menu.saludar();

       do {
          menu.opciones();
          jugarDeNuevo = Partida.repetirJuego();

        }while(jugarDeNuevo);

        System.out.println("Esperamos que haya disfrutado de su juego");

        //        System.exit(0);
    }

}
