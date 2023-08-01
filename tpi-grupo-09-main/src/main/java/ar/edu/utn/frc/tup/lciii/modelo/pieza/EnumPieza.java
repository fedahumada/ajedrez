package ar.edu.utn.frc.tup.lciii.modelo.pieza;

import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.Getter;

@Generated
public enum EnumPieza {
    TORRE_N("\u2656", Color.NEGRO, new int[][]{{-1,0},{1,0},{0,-1},{0,1}}, 'R'),
    CABALLO_N("\u2658", Color.NEGRO, new int[][]{{-1, 2}, {1,2}, {1,-2}, {2,-1}, {2,1}, {-2,1}, {-2,-1}, {-1,-2}}, 'N'),
    ALFIL_N("\u2657", Color.NEGRO, new int[][]{{-1,-1},{1,-1},{-1,1},{1,1}}, 'B'),
    REY_N("\u2654", Color.NEGRO, new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}},'K'),
    REINA_N("\u2655", Color.NEGRO, new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}},'Q'),
    PEON_N("\u2659",Color.NEGRO, new int[][]{{0,1},{1,1},{-1,1}},'-'),

    PEON_B("\u265F", Color.BLANCO, new int[][]{{0,-1},{1,-1},{-1,-1}},'-'),
    TORRE_B("\u265C", Color.BLANCO, new int[][]{{-1,0},{1,0},{0,-1},{0,1}}, 'R'),
    CABALLO_B("\u265E", Color.BLANCO, new int[][]{{-1, 2}, {1,2}, {1,-2}, {2,-1}, {2,1}, {-2,1}, {-2,-1}, {-1,-2}},'N'),
    ALFIL_B("\u265D", Color.BLANCO, new int[][]{{-1,-1},{1,-1},{-1,1},{1,1}}, 'B'),
    REINA_B("\u265B", Color.BLANCO, new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}}, 'Q'),
    REY_B("\u265A",Color.BLANCO, new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}}, 'K');

    @Getter
            private String s;
    @Getter
            private Color color;
    @Getter
            private int[][] direccionesPosibles;
    @Getter
    private char notacion;


    EnumPieza(String s, Color color, int[][] direccionesPosibles, char notacion) {
        this.s=s;
        this.color=color;
        this.direccionesPosibles=direccionesPosibles;
        this.notacion=notacion;
    }


}
