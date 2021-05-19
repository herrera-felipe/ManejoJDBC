package test;

import datos.PersonaJDBC;
import domain.Persona;
import java.util.List;


public class ManejoPesonas {
    
    public static void main(String[] args) {
        
        //Prueba de Personas
        PersonaJDBC personaJDBC = new PersonaJDBC();
        
        List<Persona> listaPersonas = personaJDBC.select();
        
        for (Persona persona : listaPersonas) {
            System.out.println("Persona: " + persona);
        }
        
    }
    
}
