/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.dao;

import tad.proyecto.entidades.Usuario;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public interface UsuariosDao {
    
    public Usuario getUsuario(final String username) throws Exception;
    
    public void addUsuario(final Usuario usuario) throws Exception;
    
}
