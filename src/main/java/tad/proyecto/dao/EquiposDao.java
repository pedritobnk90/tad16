/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.dao;

import java.util.List;
import tad.proyecto.entidades.Equipo;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public interface EquiposDao {
    
    public List<Equipo> getEquipos() throws Exception;
    
    public Equipo getEquipo(final int id) throws Exception;
        
    public Equipo getEquipo(final String nombre) throws Exception;
    
}
