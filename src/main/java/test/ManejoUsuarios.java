package test;

import datos.Conexion;
import datos.UsuarioDao;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;
import java.sql.*;
import java.util.List;

public class ManejoUsuarios {

    public static void main(String[] args) {
        
        // Conexion transaccional
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            
            if ( conexion.getAutoCommit() ) {
                conexion.setAutoCommit(false);
            }
            
            UsuarioDao usuarioDao = new UsuarioDaoJDBC(conexion);
            
            // SELECT
            List<UsuarioDTO> listaUsuarios = usuarioDao.select();
            
            for (UsuarioDTO usuario : listaUsuarios) {
            	System.out.println("Usuario DTO:" + usuario);
            }
            
            // INSERT
            UsuarioDTO nuevoUser = new UsuarioDTO();
            
            nuevoUser.setUsuario("Angelina");
            nuevoUser.setPassword("1234");
            
            usuarioDao.insert(nuevoUser);
            
            // UPDATE
            UsuarioDTO modificarUser = new UsuarioDTO();
            
            modificarUser.setId_usuario(3);
            modificarUser.setUsuario("Alejandro");
            modificarUser.setPassword("1234");
            
            usuarioDao.update(modificarUser);
            
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
