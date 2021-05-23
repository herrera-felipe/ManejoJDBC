package test;

import datos.Conexion;
import datos.UsuarioJDBC;
import domain.Usuario;
import java.sql.*;

public class ManejoUsuarios {

    public static void main(String[] args) {
        
        // Conexion transaccional
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            
            if ( conexion.getAutoCommit() ) {
                conexion.setAutoCommit(false);
            }
            
            // Establecer conexion
            UsuarioJDBC usuarioJDBC = new UsuarioJDBC(conexion);
            
            // Sentencias SQL
            
            // UPDATE
            Usuario cambioUsuario = new Usuario();
            
            cambioUsuario.setId_usuario(3);
            cambioUsuario.setUsuario("EdwinHerrera");
            cambioUsuario.setPassword("111abc");
            
            usuarioJDBC.update(cambioUsuario); 
            
            // INSERT
            Usuario nuevoUsuario = new Usuario();
            
            nuevoUsuario.setUsuario("Estela");
            nuevoUsuario.setPassword("123abc");
            
            usuarioJDBC.insert(nuevoUsuario);
            
            // Commit de la transaccion
            conexion.commit();
            System.out.println("Se ha hecho el Commit de la transaccion.");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            
            try {
                conexion.rollback();
            }
            catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        
    }

}
