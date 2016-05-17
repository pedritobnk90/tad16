/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.dao;

import java.util.List;
import tad.proyecto.entidades.Clasificacion;
import tad.proyecto.entidades.Jornada;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public interface ClasificacionDao {
    
    public void calculaClasificacion (final List<Jornada> jornada) throws Exception;
    
    public List<Clasificacion> getClasificacionActual() throws Exception;
    
    public void calculaClasificacion2 (final List<Jornada> jornadaAntes, final List<Jornada> jornadas) throws Exception;
    
    public void calculaClasificacion3 (final List<Jornada> jornadaAntes) throws Exception;
    
    public List<Clasificacion> getClasificacionActualEquipos() throws Exception;
    
    public int getMinGolesContra() throws Exception;
    
    public int getMaxGolesContra() throws Exception;
    
    public int getMinGolesFavor() throws Exception;
    
    public int getMaxGolesFavor() throws Exception;
    
    public int getMinDifGoles() throws Exception;
    
    public int getMaxDifGoles() throws Exception;
    
}
