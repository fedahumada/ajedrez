package ar.edu.utn.frc.tup.lciii.modelo.pieza.StrategyMovimiento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovimientoPieza {

    private MovimientoStrategy movimientoStrategy;

    public  void moverPieza(MovimientoStrategy movimientoStrategyP) {
        this.movimientoStrategy = movimientoStrategyP;
    }

    public static String convertirNumeroALetra(int col) {
        String result;

        switch (col) {
            case 0: result="A";
                break;
            case 1: result="B";
                break;
            case 2: result="C";
                break;
            case 3: result="D";
                break;
            case 4: result="E";
                break;
            case 5: result="F";
                break;
            case 6: result="G";
                break;
            case 7: result="H";
                break;
            default: result="-"; // valor que no sirve
        }
        return result;
    }
}
