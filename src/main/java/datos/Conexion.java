/*
 * Esta clase tendra los metodos para realizar la conexion a la base de datos. 
 * y las variables JDBC_URL, JDBC_USER, JDBC_PASS que se usaran para dicha conexion.
 */
package datos;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author felipe herrera
 */
public class Conexion {

    
    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC"; 
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin"; 

    // Pool de conexiones 
    public static DataSource getDataSource() {
    	BasicDataSource datasource = new BasicDataSource();
    	
    	// Configuracion Pool de conexiones
    	datasource.setUrl(JDBC_URL);
    	datasource.setUsername(JDBC_USER);
    	datasource.setPassword(JDBC_PASS);
    	// Tamaño inicial del pool de conexiones
    	datasource.setInitialSize(5); // 5 conexiones por default
    	
    	return datasource;
    }
    
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection(); // Establece la conexion a la base de datos.
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
