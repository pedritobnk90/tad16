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
 *
 * @author PedroMadrigal
 */
public interface ClasificacionDao {
    
    public void calculaClasificacion (final List<Jornada> jornada) throws Exception;
    
    public List<Clasificacion> getClasificacionActual() throws Exception;
    
    public void calculaClasificacion2 (final List<Jornada> jornadaAntes, final List<Jornada> jornadas) throws Exception;
    
}
