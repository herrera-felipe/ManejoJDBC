/*
 * Esta clase tendra los metodos para realizar la conexion a la base de datos. 
 * y las variables JDBC_URL, JDBC_USER, JDBC_PASS que se usaran para dicha conexion.
 */
package datos;

import java.sql.*;

/**
 *
 * @author faunus
 */
public class Conexion {

    // Variables para realizar la conexion a la base de datos.
    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC"; // contiene la direccion de la base de datos.
    private static final String JDBC_USER = "root";// contiene el usuario para conectarnos a la base de datos.
    private static final String JDBC_PASS = "admin"; // contiene el password para conectarnos a la base de datos.

    /*
     * Metodo encargado de realizar la conexion a la BD.
     * Puede tener cualquier nombre, en este caso lo llamamos igual que el metodo de DriverManager.
     * Este metodo puede arrojar una exepcion SQL por lo que se especificara con un "throws".
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS); // Se crea la conexion a la base de datos.
    }

    /*
     * Metodos para cerrar los obj de tipo ResulSet, PreparedStatement y Connection.
     * Ya que son los objetos que vamos a abrir al momento de trabajar con la BD.
     */
    public static void close(ResultSet rs) {
        // Puede arrojar exepcion.
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {

        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {

        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
