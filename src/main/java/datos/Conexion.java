/*
 * Esta clase tendra los metodos para realizar la conexion a la base de datos. 
 * y las variables JDBC_URL, JDBC_USER, JDBC_PASS que se usaran para dicha conexion.
 */
package datos;

import java.sql.*;

import javax.sql.DataSource;

/**
 *
 * @author felipe herrera
 */
public class Conexion {

    
    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC"; 
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin"; 

    // Pool de conexion 
    public static DataSource getDataSource() {
    	
    }
    
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
