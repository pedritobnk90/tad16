/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import tad.proyecto.entidades.Usuario;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public class UsuariosDaoImpl {
    
    /**
     * Este método se utiliza cuando se quiere obtener un usuario a través de su username.
     * @param username Único parámetro del método, username del usuario.
     * @return Usuario Devuelve todos los datos del usuario.
     * @exception Exception
     * @see Exception
     */
    public Usuario getUsuario(final String username) throws Exception {
        
        Usuario usuario = new Usuario();    
        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT * FROM Usuario WHERE username = '" + username + "'");
            while(rs.next()){
                usuario.setId(rs.getInt(1));
                usuario.setUsername(rs.getString(2));
                usuario.setPass(rs.getString(3));
                usuario.setNombre(rs.getString(4));
                usuario.setApellidos(rs.getString(5));
                usuario.setEquipoFavorito(rs.getInt(6));
            }
        
        } catch (final Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
    /**
     * Este método se utiliza cuando se quiere añadir un nuevo usuario.
     * @param usuario Único parámetro del método, objeto usuario con todos sus datos.
     * @return No devuelve nada.
     * @exception Exception
     * @see Exception
     */
    public void addUsuario(final Usuario usuario) throws Exception {
           
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            s.executeQuery ("INSERT INTO Usuario (username, pass, nombre, apellidos, equipoFavorito) VALUES "
                + "('" + usuario.getUsername() + "','" + usuario.getPass() + "','" + usuario.getNombre() + "','"
                + usuario.getApellidos() + "'," + usuario.getEquipoFavorito() + ")");
        } catch (final Exception e){
            e.printStackTrace();
        }
    }
        
}
