/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Felipe Herrera
 */
public class UsuarioDTO {
    
    private int id_usuario;
    private String usuario;
    private String password;

    public UsuarioDTO() {
        
    }

    public UsuarioDTO(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    

    public UsuarioDTO(int id_usuario, String usuario, String password) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.password = password;
    }
    

    public UsuarioDTO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" + 
                "id_usuario= " + getId_usuario() + "'" + 
                " usuario= " + getUsuario() + "'" + 
                " password= " + getPassword() + "'" +
                "}";
    }
    
    
    
}
