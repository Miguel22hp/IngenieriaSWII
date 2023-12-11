package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AplicacionSQLite {
    private Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos SQLite.
     */
    public AplicacionSQLite() {
        try {
            // Establece la conexión a la base de datos SQLite (asegúrate de tener el driver JDBC de SQLite)
            connection = DriverManager.getConnection("jdbc:sqlite:agricultura.db");

            // Crea las tablas si no existen
            createTables();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método privado para crear las tablas en la base de datos si no existen.
     */
    private void createTables() {
        try {
            Statement statement = connection.createStatement();

            // Tabla Terrenos
            String createTerrenosTable = "CREATE TABLE IF NOT EXISTS Terrenos (" +
                    "id INTEGER PRIMARY KEY," +
                    "tamano INTEGER," +
                    "ubicacion_lat DOUBLE," +
                    "ubicacion_lon DOUBLE" +
                    ");";
            statement.execute(createTerrenosTable);

            String createLimitesTerrenoTable = "CREATE TABLE IF NOT EXISTS LimitesTerreno (" +
                    "id INTEGER PRIMARY KEY," +
                    "idTerreno INTEGER," +
                    "latitud DOUBLE," +
                    "longitud DOUBLE," +
                    "FOREIGN KEY (idTerreno) REFERENCES Terrenos(id)" +
                    ");";
            statement.execute(createLimitesTerrenoTable);

            // Tabla Parcelas
            String createParcelasTable = "CREATE TABLE IF NOT EXISTS Parcelas (" +
                    "id INTEGER PRIMARY KEY," +
                    "idTerreno INTEGER," +
                    "ubicacion_lat DOUBLE," +
                    "ubicacion_lon DOUBLE," +
                    "FOREIGN KEY (idTerreno) REFERENCES Terrenos(id)" +
                    ");";
            statement.execute(createParcelasTable);

            // Otras tablas según sea necesario

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade un terreno a la base de datos SQLite y devuelve su identificador.
     */
    public int addTerreno(int tamano, Ubicacion[] limites, Ubicacion ubi) {
        try {
            // Inserta el terreno en la tabla Terrenos
            String insertTerrenoQuery = "INSERT INTO Terrenos (tamano, ubicacion_lat, ubicacion_lon) VALUES (?, ?, ?)";
            PreparedStatement insertTerrenoStatement = connection.prepareStatement(insertTerrenoQuery, Statement.RETURN_GENERATED_KEYS);
            insertTerrenoStatement.setInt(1, tamano);
            insertTerrenoStatement.setDouble(2, ubi.getX());
            insertTerrenoStatement.setDouble(3, ubi.getY());
            insertTerrenoStatement.executeUpdate();

            // Obtiene el identificador generado para el nuevo terreno
            ResultSet generatedKeys = insertTerrenoStatement.getGeneratedKeys();
            int idTerreno = -1;
            if (generatedKeys.next()) {
                idTerreno = generatedKeys.getInt(1);
            }

            // Si se pudo obtener el identificador, inserta los límites en la tabla LimitesTerreno
            if (idTerreno != -1) {
                addLimitesTerreno(idTerreno, limites);
            }

            return idTerreno;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Añade límites a la tabla LimitesTerreno para un terreno específico.
     * @param idTerreno Identificador del terreno al que se agregarán los límites.
     * @param limites Array de ubicaciones que representan los límites.
     */
    private void addLimitesTerreno(int idTerreno, Ubicacion[] limites) {
        try {
            String insertLimitesQuery = "INSERT INTO LimitesTerreno (idTerreno, latitud, longitud) VALUES (?, ?, ?)";
            PreparedStatement insertLimitesStatement = connection.prepareStatement(insertLimitesQuery);

            for (Ubicacion limite : limites) {
                // No es necesario proporcionar un valor para el campo id, se asignará automáticamente
                insertLimitesStatement.setInt(1, idTerreno);
                insertLimitesStatement.setDouble(2, limite.getX());
                insertLimitesStatement.setDouble(3, limite.getY());
                insertLimitesStatement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método te da los datos que le pides acerca del terreno que le indicas
     * @param elementosMod un array de enteros de 3 elementos, en los cuales se indica con 0 el elemento que quieres
     *                     que te devuelva, y con un 1 los que no.
     * @param idTerreno el identificador del terreno del que obtener los datos.
     * @return un array con los objetos que has pedido.
     * @throws NoTerrenoException si el @param idTerreno que le pasas no se corresponde a ningún terreno en el sistema
     */
    public Object[] getTerreno(int[] elementosMod, int idTerreno) throws NoTerrenoException {
        Object[] result = new Object[3];

        try {

            // Consulta para obtener los datos del terreno desde la base de datos
            String query = "SELECT tamano, ubicacion_lat, ubicacion_lon FROM Terrenos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTerreno);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (elementosMod[0] == 0) {
                    result[0] = resultSet.getInt("tamano");
                } else {
                    result[0] = null;
                }

                if (elementosMod[1] == 0) {
                    // Puedes ajustar esto según la estructura real de tu base de datos
                    // Por ejemplo, si tus límites están en otra tabla, necesitarías una consulta adicional
                    result[1] = obtenerLimitesTerreno(idTerreno);
                } else {
                    result[1] = null;
                }

                if (elementosMod[2] == 0) {
                    int ubicacion_lat = resultSet.getInt("ubicacion_lat");
                    int ubicacion_lon = resultSet.getInt("ubicacion_lon");
                    result[2] = new Ubicacion(ubicacion_lat, ubicacion_lon);
                } else {
                    result[2] = null;
                }
            } else {
                throw new NoTerrenoException("No existe terreno con ese id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Método privado para obtener los límites de un terreno desde la base de datos.
     * @param idTerreno el identificador del terreno.
     * @return Array de ubicaciones que representan los límites del terreno.
     */
    private Ubicacion[] obtenerLimitesTerreno(int idTerreno) {
        List<Ubicacion> limites = new ArrayList<>();

        try {
            // Consulta para obtener los límites desde la base de datos
            String query = "SELECT latitud, longitud FROM LimitesTerreno WHERE idTerreno = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTerreno);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int latitud = resultSet.getInt("latitud");
                int longitud = resultSet.getInt("longitud");
                limites.add(new Ubicacion(latitud, longitud));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return limites.toArray(new Ubicacion[0]);
    }
}
