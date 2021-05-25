/*
 * Esta clase contiene los metodos SELECT, INSERT, UPDATE y DELETE para la tabla de persona.
 */
package datos;

import java.sql.*;
import java.util.*;
import domain.PersonaDTO;

public class PersonaDaoJDBC implements PersonaDao {

    private Connection conexionTransaccional;
    
    // Definimos las sentencias SQL de SELECT, INSERT, UPDATE y DELETE.
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE  id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";

    
    public PersonaDaoJDBC() {
        
    }
    
    public PersonaDaoJDBC(Connection conexicionTransaccional) {
        this.conexionTransaccional = conexicionTransaccional;
    }
    
    /*
     * Metodo que ejecutara la sentencia SQL_SELECT.
     * Este metodo listara todos los registros de la tabla personas de la BD.
     */
    public List<PersonaDTO> select() throws SQLException {
        // instanciamos los objetos Connection, PreparedStatement, ResultSet y Persona. Que usaremos para la consulta a la BD. Y la Lista de tipo Persona
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection(); //obtenemos la conexion a la BD
            stmt = conn.prepareStatement(SQL_SELECT); //inicializamos el obj de tipo PreparedStatement y especificamos la sentencia sql a usar.
            // ejecutamos el Query asignando el stmt a rs
            rs = stmt.executeQuery();

            // ciclo while y metodo next para recorrer los registros que nos ha devuelto la sentencia.
            while (rs.next()) {
                // definimos variables temporales para los datos de las columnas de la tabla persona.
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                // Creamos el Obj. de tipo Persona con los valores obtenidos.
                persona = new PersonaDTO();

                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEmail(email);
                persona.setTelefono(telefono);

                listaPersonas.add(persona); // Agregamos el "Obj. persona" a la lista "personas".
            }
        }  
        finally {
            Conexion.close(rs); // Cerramos ResultSet  "ejecucion del Query".
            Conexion.close(stmt); // Cerramos la sentencia.
            
            if ( this.conexionTransaccional == null ) {
                Conexion.close(conn); // Cerramos la conexion.
            }    
        }
        // Return el listado de personas de la tabla.
        return listaPersonas;
    }

    /*
     * Este metodo sera encargado de "insertar" valores a la tabla personas.
     * es decir inserta personas a la BD.
     */
    public int insert(PersonaDTO persona) throws SQLException {
        // instanciamos los objetos Connection, PreparedStatement.
        Connection conn = null;
        PreparedStatement stmt = null;
        // creamos una variable int  para saber cuantos registros han sido afectados.
        int registrosAfectados = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT); //inicializamos el obj de tipo PreparedStatement y especificamos la sentencia sql a usar.

            // DEFINIMOS LOS VALORES QUE REQUIERE LA SENTENCIA INSERT
            stmt.setString(1, persona.getNombre()); // indice -> 1, valor a proporcionar -> .getNombre()
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            // msj a la consonla
            System.out.println("Ejecutando query: " + SQL_INSERT);
            // Ejecutamos la sentencia
            registrosAfectados = stmt.executeUpdate(); // retorna int con el # de registros modificados, lo asignamos a rows.

            System.out.println("Registros afectados: " + registrosAfectados);
        } 
        finally {
            Conexion.close(stmt); 
            
            if ( this.conexionTransaccional == null ) {
                Conexion.close(conn); // Cerramos la conexion.
            }    
        }
        // Regresamos el numero de registros afectados.
        return registrosAfectados;
    }

    /* 
     * Este metodo modificara registros existentes en la BD, mediante el id_persona.
     * por lo tanto tendra que recibir un obt de tipo Persona, para poder acceder a este valor
     * y asi realizar la busqueda del registro y modificarlo.
     */
    public int update(PersonaDTO persona) throws SQLException {
        // instanciamos los objetos Connection, PreparedStatement.
        Connection conn = null;
        PreparedStatement stmt = null;
        // creamos una variable int  para saber cuantos registros han sido afectados.
        int registrosAfectados = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();

            System.out.println("Ejecuntando query: " + SQL_UPDATE);
            
            stmt = conn.prepareStatement(SQL_UPDATE); //inicializamos el obj de tipo PreparedStatement y especificamos la sentencia sql a usar.
            
            // Definimos los valores que requiere la sentencia SQL_UPDATE
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            // Pedimos el id_persona que queremos modificar.
            stmt.setInt(5, persona.getId_persona());
            
            // Ejecutamos la sentencia
            registrosAfectados = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + registrosAfectados);
            
        } 
        finally {
            Conexion.close(stmt); // cerramos la sentencia
            
            if ( this.conexionTransaccional == null ) {
                Conexion.close(conn); // Cerramos la conexion.
            }    
        }
        
        return registrosAfectados;
    }
    
    /*
     * Este metodo se encargara de eliminar registros de la tabla personas de la BD.
     * Recibira por parametro un Obj de tipo persona, aunque solo nos intereza el id_persona
     * para eliminar el registro.
     */
    public int delete(PersonaDTO persona) throws SQLException {
        // Definimos el obj de conexion y el PreparedStatement
        Connection conn = null;
        PreparedStatement stmt = null;
        int registrosAfectados = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection(); // Establecemos conexion con la BD
            
            System.out.println("Ejecutando query: " + SQL_DELETE); // Msj a consola
            
            stmt = conn.prepareStatement(SQL_DELETE); // Sentencia sql a ejecutar.
            stmt.setInt(1, persona.getId_persona()); // Solicitamos el id_persona, del registro a eliminar
            
            registrosAfectados = stmt.executeUpdate(); // Ejecutamos la sentencia.
            System.out.println("Registros eliminados: " + registrosAfectados);
        } 
        finally {
            // Cerramos los Objetos.
            Conexion.close(stmt);
            
            if ( this.conexionTransaccional == null ) {
                Conexion.close(conn); // Cerramos la conexion.
            }    
        }
        
        return registrosAfectados;
    }
    
}
