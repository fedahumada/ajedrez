package ar.edu.utn.frc.tup.lciii.dbManager;


import ar.edu.utn.frc.tup.lciii.modelo.partida.EnumEstadoJuego;
import ar.edu.utn.frc.tup.lciii.modelo.partida.Partida;
import ar.edu.utn.frc.tup.lciii.modelo.partida.RecuperarDatos;
import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public  class dbService {

    private static dbService instancia;



    public static dbService getInstance() {
        if (instancia == null) {
            instancia = new dbService();
            instancia.iniciarDB();
            instancia.insertarPartidasHard();

        }
        return instancia;
    }

    private void insertarPartidasHard() {
        String query = "INSERT INTO Partidas (FECHA, JUGADORES, ANOTACIONPIEZAS, ESTADO, PIEZASTABLERO) VALUES " +
                "('2023-06-15', 'J1.Fede||J2.Franco', 'A4-E5-D4-E4-RA3-KE7-RE3-KE6-RxE4-KE7', 'EnumEstadoJuego.OK', 'RN70|NN71|BN72|QN73|BN75|NN76|RN77|-N60|-N61|-N62|-N63|KN64|-N65|-N66|-N67|-B30|-B33|RB34|-B11|-B12|-B14|-B15|-B16|-B17|NB01|BB02|QB03|KB04|BB05|NB06|RB07'), "+
                "('2023-06-01', 'J1.Lucas||J2.Ronaldo', 'D4-C6-BF4-D6-BxD6','EnumEstadoJuego.OK','RN70|NN71|BN72|QN73|KN74|BN75|NN76|RN77|-N60|-N61|-N64|-N65|-N66|-N67|-N52|BB53|-B33|-B10|-B11|-B12|-B14|-B15|-B16|-B17|RB00|NB01|QB03|KB04|BB05|NB06|RB07'), " +
                "('2023-07-01', 'J1.Kraftkhov||J2.some kid', 'B3-G6-BA3-D5-C4-QD6-B4-NC6', 'EnumEstadoJuego.OK','RN70|BN72|KN74|BN75|NN76|RN77|-N60|-N61|-N62|-N64|-N65|-N67|NN52|QN53|-N56|-N43|-B31|-B32|BB20|-B10|-B13|-B14|-B15|-B16|-B17|RB00|NB01|QB03|KB04|BB05|NB06|RB07'), "+
                "('2023-07-01', 'J1.Fede||J2.Si mismo', 'E4-F5-QE2-H5-E4xF5','EnumEstadoJuego.OK','RN70|NN71|BN72|QN73|KN74|BN75|NN76|RN77|-N60|-N61|-N62|-N63|-N64|-N66|-B45|-N47|-B10|-B11|-B12|-B13|QB14|-B15|-B16|-B17|RB00|NB01|BB02|KB04|BB05|NB06|RB07|'), "+
                "('2023-07-01', 'J1.Wish||J2.I knew','C4-A5-QB3-RA6-NC3-RB6-NB5','EnumEstadoJuego.OK','NN71|BN72|QN73|KN74|BN75|NN76|RN77|-N61|-N62|-N63|-N64|-N65|-N66|-N67|RN51|-N40|NB41|-B32|QB21|-B10|-B11|-B13|-B14|-B15|-B16|-B17|RB00|BB02|KB04|BB05|NB06|RB07|'), "+
                "('2023-07-01','J1.Wish||J2.I knew','C4-A5-QB3-RA6-NC3-RB6-NB5-RE6-F4-RxE2-KD1-RF2-KE1-RE2-KxE2-NC6-NA7-ND4','EnumEstadoJuego.JAQUE','BN72|QN73|KN74|BN75|NN76|RN77|NB60|-N61|-N62|-N63|-N64|-N65|-N66|-N67|-N40|-B32|NN33|-B35|QB21|-B10|-B11|-B13|KB14|-B16|-B17|RB00|BB02|BB05|NB06|RB07|')";
        try (PreparedStatement statement = dbClass.getInstance().getConexion().prepareStatement(query)) {


            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
            } else {
                System.out.println("No se ha podido guardar la partida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void iniciarDB() {
        dbClass.getInstance().startServer();
    }


    public void guardarPartida(java.sql.Date fecha, EnumEstadoJuego estado, String jugadores, String anotacionPiezas, String piezasTablero) {

        String query = "Insert INTO Partidas(Fecha, jugadores, anotacionPiezas,estado, piezasTablero) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = dbClass.getInstance().getConexion().prepareStatement(query)) {
            statement.setDate(1, fecha);
            statement.setString(2, jugadores);
            statement.setString(3, anotacionPiezas);
            statement.setString(4, estado.toString());
            statement.setString(5, piezasTablero);


            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("La partida se ha guardado correctamente.");
            } else {
                System.out.println("No se ha podido guardar la partida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> obtenerListaPartidas() {
        List<String> partidasGuardadas = new ArrayList<>();
        List<String> jugadoresAux = new ArrayList<>();
        String auxConcat = "";
        String selectPartidas = "SELECT * FROM Partidas";
        try (Statement statement2 = dbClass.getInstance().getConexion().createStatement();
             ResultSet resultSet = statement2.executeQuery(selectPartidas)) {
            while (resultSet.next()) {
                String idPartida = resultSet.getString("IDPARTIDA");
                java.sql.Date Fecha = resultSet.getDate("FECHA");
                String Jugadores = resultSet.getString("JUGADORES");
                String anotacionPiezas = resultSet.getString("anotacionPIEZAS");
                String Estado = resultSet.getNString("ESTADO");
                String piezasTablero = resultSet.getString("piezasTablero");

                jugadoresAux = RecuperarDatos.recuperarJugadores(Jugadores);

                auxConcat = "(" + idPartida + ") " + jugadoresAux.get(0) + " VS " + jugadoresAux.get(1) + " | " + Fecha.toString();
                partidasGuardadas.add(auxConcat);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidasGuardadas;
    }

    @Generated
    public static void buscarPartida(Integer id) {
        Partida respuesta = null;
        List<String> jugadoresAux = new ArrayList<>();

        String selectPartidas = "SELECT * FROM Partidas WHERE idPartida = " + id.toString();
        try (Statement statement2 = dbClass.getInstance().getConexion().createStatement();
             ResultSet resultSet = statement2.executeQuery(selectPartidas)) {

            if (resultSet.next()) {
                String idPartida = resultSet.getString("IDPARTIDA");
                java.sql.Date fecha = resultSet.getDate("FECHA");
                String jugadores = resultSet.getString("JUGADORES");
                String anotacionPiezas = resultSet.getString("anotacionPIEZAS");
                String estado = resultSet.getString("ESTADO");
                String piezasTablero = resultSet.getString("piezasTablero");

                jugadoresAux = RecuperarDatos.recuperarJugadores(jugadores);
                Tablero.LimpiarTablero();
                Tablero.ObtenerTableroVacio();
                String[] auxPiezasRecuperadas =RecuperarDatos.recuperarPiezasEnTableroGuardado(piezasTablero);
                Tablero.setearPiezasGuardadas(auxPiezasRecuperadas);
                Partida auxPartida = new Partida(
                        jugadoresAux.get(0),
                        jugadoresAux.get(1),
                        RecuperarDatos.recuperarAnotaciones(anotacionPiezas),
                        RecuperarDatos.recuperarEnumEstado(estado)
                );
                respuesta = auxPartida;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


