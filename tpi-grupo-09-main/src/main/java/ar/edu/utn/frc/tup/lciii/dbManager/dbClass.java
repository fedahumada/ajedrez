package ar.edu.utn.frc.tup.lciii.dbManager;
import ar.edu.utn.frc.tup.lciii.modelo.utils.Generated;
import lombok.Data;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Data

public class dbClass {
    private static dbClass instancia;
    private Server server;
    private Connection conexion;

    private dbClass() {
    }

    public static dbClass getInstance() {
        if (instancia == null) {
            instancia = new dbClass();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void startServer(){
        try {
            server = Server.createTcpServer().start();

            this.createDatabase();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Generated
    public void stopServer() {
        if (server != null) {
            server.stop();

        }
    }

    public void createDatabase() throws SQLException {
        try {
            String jdbcUrl = "jdbc:h2:mem:ajedrezdb";
            conexion = DriverManager.getConnection(jdbcUrl);
            this.createTables();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createTables()  {
        try (Statement statement = conexion.createStatement()) {

            String dropTable = "DROP TABLE IF EXISTS Partidas";
            statement.executeUpdate(dropTable);

            String tablaPartidas = "CREATE TABLE IF NOT EXISTS Partidas (" +
                    "IDPARTIDA INT PRIMARY KEY AUTO_INCREMENT," +
                    "FECHA date," +
                    "JUGADORES varchar(255)," +
                    "ANOTACIONPIEZAS varchar(255)," +
                    "ESTADO varchar(42)," +
                    "PIEZASTABLERO varchar(255)" +
                    ")";
            statement.executeUpdate(tablaPartidas);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Generated
    public void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
