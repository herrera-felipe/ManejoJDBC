/*
 * Esta clase contendra todos los atributos que corresponden a cada una de las columnas
 * de nuestra tabla personas de la BD
*/
package domain;

public class PersonaDTO {

    private int id_persona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public PersonaDTO() {
        
    }
    
    public int getId_persona() {
        return this.id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "{" +
            " id_persona='" + getId_persona() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
    
}
