/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.dao;

import java.util.List;
import tad.proyecto.entidades.Jornada;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public interface JornadasDao {
    
    public List<Jornada> getJornada (final int idJornada) throws Exception;
    
    public List<Integer> getJornadasTerminadas() throws Exception;
    
    public List<Jornada> getJornadas() throws Exception;
    
    public void updateJornadas(final List<Jornada> jornadas) throws Exception;
    
    public int getSiguienteJornada() throws Exception;
    
    public void borrarJornadas(final List<Jornada> jornadas) throws Exception;
    
}
