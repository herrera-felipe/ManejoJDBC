package test;

import datos.*;
import domain.*;
import java.util.*;

public class ManejoUsuarios {

    public static void main(String[] args) {
        
        
        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();

        List<Usuario> listaUsuarios = usuarioJDBC.select();

        // Recorremos con un for
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Usuarios: " + usuario);
        }
        
        // Insertar nuevo usuario
        Usuario user = new Usuario("carlos ", "1234");
        usuarioJDBC.insert(user);
        
    }

}
