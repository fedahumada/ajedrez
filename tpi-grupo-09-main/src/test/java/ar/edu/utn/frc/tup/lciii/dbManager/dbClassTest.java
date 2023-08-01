package ar.edu.utn.frc.tup.lciii.dbManager;

import ar.edu.utn.frc.tup.lciii.modelo.tablero.Tablero;
import org.h2.tools.Server;
import java.lang.reflect.Method;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class dbClassTest {
    private dbClass dbInstance;
    private Server server;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        dbInstance = dbClass.getInstance();
        Tablero.LimpiarTablero();
    }

    @AfterEach
    public void tearDown() {
        dbInstance.closeConnection();
    }

    @Test
    @DisplayName("Test startServer")
    public void testStartServer() {
        dbInstance.startServer();
        server = dbInstance.getServer();
        assertNotNull(server);
        assertTrue(server.isRunning(true));
    }

    @Test
    @DisplayName("Test stopServer")
    public void testStopServer() {
        dbInstance.startServer();
        server = dbInstance.getServer();
        assertNotNull(server);
        assertTrue(server.isRunning(true));

        dbInstance.stopServer();
        assertFalse(server.isRunning(true));
    }

    @Test
    @DisplayName("Test createDatabase")
    public void testCreateDatabase() throws SQLException {
        dbInstance.startServer();
        dbInstance.createDatabase();

        connection = dbInstance.getConexion();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
    }

    @Test
    @DisplayName("Test createTables")
    public void testCreateTables() throws Exception {
        dbInstance.startServer();
        dbInstance.createDatabase();

        connection = dbInstance.getConexion();
        assertNotNull(connection);

        Method createTablesMethod = dbClass.class.getDeclaredMethod("createTables");
        createTablesMethod.setAccessible(true);
        createTablesMethod.invoke(dbInstance);

        try (Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("SELECT * FROM Partidas"));
        }
    }
}
