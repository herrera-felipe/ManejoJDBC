/*
 * Esta clase tendra las sentencias SQL para el USUARIO
 */
package datos;

import domain.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioJDBC {

    //Sentencias SQL select, insert, update y delete
    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE  id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    
    /*
     * Metodo SELECT, ejecutara la sentencia SQL y listara los registros de la tabla
    */
    public List<Usuario> select() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Usuario obj_usuario = null;
        List<Usuario> listaUsuarios = new ArrayList<Usuario>(); // Instanciamos la Lista que almacenara los registros
        
        try {
            conn = Conexion.getConnection();  // Establecer Conexión a la Base de Datos.
            stmt = conn.prepareStatement(SQL_SELECT); // Asignamos la Sentencia SQL al obj PreparedStatement
            // Ejecucion del Query
            rs = stmt.executeQuery();
            
            // ciclo while y metodo next para recorrer los registros que nos ha devuelto la sentencia.
            while (rs.next()) {
                // Definir los valores obtenidos por el regsitro
                int id_usuario = rs.getInt("id_usuario");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                
                // Instanciamos un Obj de tipo Usuario para asignar los valores obtenidos
                obj_usuario = new Usuario();
                
                obj_usuario.setId_usuario(id_usuario);
                obj_usuario.setUsuario(usuario);
                obj_usuario.setPassword(password);
                
                //Agregamos el obj usuario a la Lista de usuarios
                listaUsuarios.add(obj_usuario);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            // Cerramos nuestros Objetos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return listaUsuarios;
    }
    
    /*
     * Metodo INSERT, ejecutara la sentencia INSERT para agregar valores a la tabla Usuarios.
     * Por parametro recibira un Obj del tipo Usuario
    */
    public int insert(Usuario usuarioNuevo) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registrosAfectados = 0; // rows
        
        try {
            conn = Conexion.getConnection(); // Obtener conexion con la Base de Datos;
            stmt = conn.prepareStatement(SQL_INSERT); // Preparamos la sentencia SQL.
            
            // obtenemos los valores a insertar por la sentencia SQL
            stmt.setString(1, usuarioNuevo.getUsuario());
            stmt.setString(2, usuarioNuevo.getPassword());
            
            System.out.println("Ejecutanco Query: " + SQL_INSERT);
            
            // Se ejecuta la sentencia
            registrosAfectados = stmt.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace(System.out); // En caso de error imprime la pila
        }
        finally {
            Conexion.close(stmt); // Cerramos sentencia
            Conexion.close(conn); // Cerramos Conexion a la BD
        }
        return registrosAfectados;
    }
    
    /*
     * Metodo update, modificara los registros en la BD del id_usuario asociado
    */
    public int update(Usuario usuario) {
        // instanciamos Connection, PreparedStatement
        Connection conn = null;
        PreparedStatement stmt = null;
        
        int registrosAfectados = 0;
        
        try {
            //Establecer conexion con la BD
            conn = Conexion.getConnection();
            
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            
            // Preparamos la sentencia a ejecutar
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            // Definimos los valores requeridos por el update
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getId_usuario());
            
            // Ejecutamos la sentecia
            registrosAfectados = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + registrosAfectados);
        } 
        catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return registrosAfectados;
    }
    
    /*
     * Metodo delete: Elimina registros de usuario en la BD mediante el Id_usuario
    */
    public int delete(Usuario usuario) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registrosAfectados = 0;
        
        try {
            // Conexion a la BD
            conn = Conexion.getConnection();
            
            System.out.println("Ejecutando query: " + SQL_DELETE);
            
            stmt = conn.prepareStatement(SQL_DELETE); // sentencia SQL a ejecutar
            
            stmt.setInt(1, usuario.getId_usuario()); // Id solicitado del a}registro a eliminar
            
            registrosAfectados = stmt.executeUpdate(); // Ejecucion del SQL
            System.out.println("Registros eliminados: " + registrosAfectados);
        } 
        catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return registrosAfectados;
    }
}
