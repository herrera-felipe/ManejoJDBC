package test;

import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;
import java.sql.*;

public class ManejoPersonas {
    
    public static void main(String[] args) {
        
        // Conexion Transaccional
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            
            if (conexion.getAutoCommit()) { // si AutoCommit = true
                conexion.setAutoCommit(false); 
            }
            
            // Establecer conexion Transaccional
            PersonaJDBC personaJDBC = new PersonaJDBC(conexion);
            
            // sentencias a ejecutar dentro de la transaccion
            
            // Update
            Persona cambioPersona = new Persona();
            cambioPersona.setId_persona(2);
            cambioPersona.setNombre("Andres Felipe");
            cambioPersona.setApellido("Herrera");
            cambioPersona.setEmail("fherrera@email.com");
            cambioPersona.setTelefono("12347665");
            
            personaJDBC.update(cambioPersona); // Actualiza obj en la BD
            
            // Insert
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Carlos");
            nuevaPersona.setApellido("Gomez"); 
            
            personaJDBC.insert(nuevaPersona);
            
            // Commit de la Transaccion
            conexion.commit();
            
            System.out.println("Commit Realizado.");
        } 
        catch (SQLException ex) {
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
