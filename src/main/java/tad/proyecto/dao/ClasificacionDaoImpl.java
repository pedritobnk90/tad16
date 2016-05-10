/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.dao;

import com.vaadin.ui.Notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tad.proyecto.entidades.Clasificacion;
import tad.proyecto.entidades.Jornada;

/**
 *
 * @author PedroMadrigal
 */
public class ClasificacionDaoImpl implements ClasificacionDao {
    
    public void calculaClasificacion (final List<Jornada> jornadas) throws Exception {
        
        List<Clasificacion> clasificacion =  calculosPrevios(jornadas);
        
        try {
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET golesFavor = " + clasif.getGolesFavor() + ", golesContra = " 
                    + clasif.getGolesContra() + ", difGoles = " + clasif.getDifGoles() + ", puntos = " 
                    + clasif.getPuntos() + " WHERE idEquipo = " + clasif.getIdEquipo());
            }
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba los goles y puntos de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
        
        clasificacion.clear();
        clasificacion = getClasificacionActual();
        
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            int posicion = 1;
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET posicion = " + posicion 
                    + " WHERE idEquipo = " + clasif.getIdEquipo());
                posicion++;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba las posiciones de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
        
        
    }

    private List<Clasificacion> calculosPrevios(final List<Jornada> jornadas) {
     
        List<Clasificacion> clasificacionActual = new ArrayList<Clasificacion>();
        try {
            clasificacionActual = getClasificacionActual();
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras se obtenía la clasificación actual.", Notification.Type.ERROR_MESSAGE);
        }
            
        for(final Jornada jornada : jornadas){
            
            for(final Clasificacion clasificacion : clasificacionActual){
                if(clasificacion.getIdEquipo() == jornada.getIdEquipoLocal()){
                    clasificacion.setGolesFavor(clasificacion.getGolesFavor() + jornada.getGolesEquipoLocal());
                    clasificacion.setGolesContra(clasificacion.getGolesContra() + jornada.getGolesEquipoVisitante());
                    clasificacion.setDifGoles(clasificacion.getDifGoles() + jornada.getGolesEquipoLocal() 
                        + jornada.getGolesEquipoVisitante());
                    if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                        clasificacion.setPuntos(clasificacion.getPuntos() + 1);
                    }else if(jornada.getGolesEquipoLocal() > jornada.getGolesEquipoVisitante()){
                        clasificacion.setPuntos(clasificacion.getPuntos() + 3);
                    }
                    break;
                }
                if(clasificacion.getIdEquipo() == jornada.getIdEquipoVisitante()){
                    clasificacion.setGolesFavor(clasificacion.getGolesFavor() + jornada.getGolesEquipoVisitante());
                    clasificacion.setGolesContra(clasificacion.getGolesContra() + jornada.getGolesEquipoLocal());
                    clasificacion.setDifGoles(clasificacion.getDifGoles() + jornada.getGolesEquipoLocal() 
                        + jornada.getGolesEquipoVisitante());
                    if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                        clasificacion.setPuntos(clasificacion.getPuntos() + 1);
                    }else if(jornada.getGolesEquipoLocal() < jornada.getGolesEquipoVisitante()){
                        clasificacion.setPuntos(clasificacion.getPuntos() + 3);
                    }
                    break;
                }
            }
            
        }
        
        return clasificacionActual;
        
    }
    
    public List<Clasificacion> getClasificacionActual() throws Exception {
        
        List<Clasificacion> clasificacion = new ArrayList<Clasificacion>();
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT * FROM Clasificacion WHERE idLiga = 1 "
                + "ORDER BY puntos DESC, difGoles DESC, golesFavor DESC, golesContra ASC");
            while(res.next()) {
                clasificacion.add(new Clasificacion(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4),
                    res.getInt(5), res.getInt(6), res.getInt(7), res.getInt(8)));
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return clasificacion;
    }
    
    
     public void calculaClasificacion2 (final List<Jornada> jornadaAntes, final List<Jornada> jornadas) throws Exception {
        
        List<Clasificacion> clasificacion =  calculosPrevios2(jornadaAntes, jornadas);
        
        try {
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET golesFavor = " + clasif.getGolesFavor() + ", golesContra = " 
                    + clasif.getGolesContra() + ", difGoles = " + clasif.getDifGoles() + ", puntos = " 
                    + clasif.getPuntos() + " WHERE idEquipo = " + clasif.getIdEquipo());
            }
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba los goles y puntos de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
        
        clasificacion.clear();
        clasificacion = getClasificacionActual();
        
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            int posicion = 1;
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET posicion = " + posicion 
                    + " WHERE idEquipo = " + clasif.getIdEquipo());
                posicion++;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba las posiciones de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
    }
     
    /*
     * Devuelve la clasificación nueva después de calcular los nuevos goles y puntos.
     * Se diferencia con calculosPrevios1 en que aquí ya se había calculado una clasificación anteriormente para una jornada.
     * Aquí calcula una clasificación para una actualización de los resultados de una jornada.
     */ 
    private List<Clasificacion> calculosPrevios2(final List<Jornada> jornadaAntes, final List<Jornada> jornadas) {
     
        List<Clasificacion> clasificacionActual = new ArrayList<Clasificacion>();
        try {
            clasificacionActual = getClasificacionActual();
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras se obtenía la clasificación actual.", Notification.Type.ERROR_MESSAGE);
        }
            
        for(final Jornada jornada : jornadas){
            
            for(final Jornada jornadaAnterior : jornadaAntes){
            
                for(final Clasificacion clasificacion : clasificacionActual){
                    
                    if(clasificacion.getIdEquipo() == jornada.getIdEquipoLocal() 
                        && clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoLocal()){
                        
                        if(jornada.getGolesEquipoLocal() != jornadaAnterior.getGolesEquipoLocal()){
                            if(jornadaAnterior.getGolesEquipoLocal() > jornada.getGolesEquipoLocal()){
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    - (jornadaAnterior.getGolesEquipoLocal() - jornada.getGolesEquipoLocal()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() - ((jornadaAnterior.getGolesEquipoLocal() 
                                    + jornadaAnterior.getGolesEquipoVisitante()) - (jornada.getGolesEquipoLocal() 
                                    + jornada.getGolesEquipoVisitante())));
                            }else{
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    + (jornada.getGolesEquipoLocal() - jornadaAnterior.getGolesEquipoLocal()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() + ((jornada.getGolesEquipoLocal() 
                                    + jornada.getGolesEquipoVisitante()) - (jornadaAnterior.getGolesEquipoLocal() 
                                    + jornadaAnterior.getGolesEquipoVisitante())));
                            }
                        }
                        
                        if(jornada.getGolesEquipoVisitante() != jornadaAnterior.getGolesEquipoVisitante()){
                            if(jornadaAnterior.getGolesEquipoVisitante() > jornada.getGolesEquipoVisitante()){
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    - (jornadaAnterior.getGolesEquipoVisitante() - jornada.getGolesEquipoVisitante()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() - ((jornadaAnterior.getGolesEquipoVisitante()
                                    + jornadaAnterior.getGolesEquipoLocal()) - (jornada.getGolesEquipoVisitante()
                                    + jornada.getGolesEquipoLocal())));
                                
                            }else{
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    + (jornada.getGolesEquipoVisitante() - jornadaAnterior.getGolesEquipoVisitante()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() + ((jornada.getGolesEquipoVisitante()
                                    + jornada.getGolesEquipoLocal()) - (jornadaAnterior.getGolesEquipoVisitante()
                                    + jornadaAnterior.getGolesEquipoLocal())));
                            }
                        }
                        
                        
                        if(jornadaAnterior.getGolesEquipoLocal() == jornadaAnterior.getGolesEquipoVisitante()){
                            if(jornada.getGolesEquipoLocal() != jornada.getGolesEquipoVisitante()){
                                if(jornada.getGolesEquipoLocal() > jornada.getGolesEquipoVisitante()){
                                    clasificacion.setPuntos(clasificacion.getPuntos() + 2);
                                }else{
                                    clasificacion.setPuntos(clasificacion.getPuntos() - 2);
                                }
                            }
                        }else if(jornadaAnterior.getGolesEquipoLocal() > jornadaAnterior.getGolesEquipoVisitante()){
                            if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 2);
                            } else if(jornada.getGolesEquipoLocal() < jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 3);
                            }
                        }else{
                            if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 1);
                            } else if(jornada.getGolesEquipoLocal() > jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 3);
                            }
                        }
                        break;
                    }
                    if(clasificacion.getIdEquipo() == jornada.getIdEquipoVisitante()
                        && clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoVisitante()){
                        
                        if(jornada.getGolesEquipoVisitante() != jornadaAnterior.getGolesEquipoVisitante()){
                            if(jornadaAnterior.getGolesEquipoVisitante() > jornada.getGolesEquipoVisitante()){
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    - (jornadaAnterior.getGolesEquipoVisitante() - jornada.getGolesEquipoVisitante()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() - ((jornadaAnterior.getGolesEquipoVisitante()
                                    + jornadaAnterior.getGolesEquipoLocal()) - (jornada.getGolesEquipoVisitante()
                                    + jornada.getGolesEquipoLocal())));
                            }else{
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    + (jornada.getGolesEquipoVisitante() - jornadaAnterior.getGolesEquipoVisitante()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() + ((jornada.getGolesEquipoVisitante()
                                    + jornada.getGolesEquipoLocal()) - (jornadaAnterior.getGolesEquipoVisitante()
                                    + jornadaAnterior.getGolesEquipoLocal())));
                            }
                        }
                        
                        if(jornada.getGolesEquipoLocal() != jornadaAnterior.getGolesEquipoLocal()){
                            if(jornadaAnterior.getGolesEquipoLocal() > jornada.getGolesEquipoLocal()){
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    - (jornadaAnterior.getGolesEquipoLocal() - jornada.getGolesEquipoLocal()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() - ((jornadaAnterior.getGolesEquipoLocal()
                                    + jornadaAnterior.getGolesEquipoVisitante()) - (jornada.getGolesEquipoLocal()
                                    + jornada.getGolesEquipoVisitante())));
                                
                            }else{
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    + (jornada.getGolesEquipoLocal() - jornadaAnterior.getGolesEquipoLocal()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() + ((jornada.getGolesEquipoLocal()
                                    + jornada.getGolesEquipoVisitante()) - (jornadaAnterior.getGolesEquipoLocal()
                                    + jornadaAnterior.getGolesEquipoVisitante())));
                            }
                        }
                        
                        
                        if(jornadaAnterior.getGolesEquipoVisitante() == jornadaAnterior.getGolesEquipoLocal()){
                            if(jornada.getGolesEquipoVisitante() != jornada.getGolesEquipoLocal()){
                                if(jornada.getGolesEquipoVisitante() > jornada.getGolesEquipoLocal()){
                                    clasificacion.setPuntos(clasificacion.getPuntos() + 2);
                                }else{
                                    clasificacion.setPuntos(clasificacion.getPuntos() - 2);
                                }
                            }
                        }else if(jornadaAnterior.getGolesEquipoVisitante() > jornadaAnterior.getGolesEquipoLocal()){
                            if(jornada.getGolesEquipoVisitante() == jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 2);
                            } else if(jornada.getGolesEquipoVisitante() < jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 3);
                            }
                        }else{
                            if(jornada.getGolesEquipoVisitante() == jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 1);
                            } else if(jornada.getGolesEquipoVisitante() > jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 3);
                            }
                        }
                        break;
                    }
                }
            }
        }
        return clasificacionActual;
    }
    
    public void calculaClasificacion3 (final List<Jornada> jornadaAntes) throws Exception {
        
        List<Clasificacion> clasificacion =  calculosPrevios3(jornadaAntes);
        
        try {
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET golesFavor = " + clasif.getGolesFavor() + ", golesContra = " 
                    + clasif.getGolesContra() + ", difGoles = " + clasif.getDifGoles() + ", puntos = " 
                    + clasif.getPuntos() + " WHERE idEquipo = " + clasif.getIdEquipo());
            }
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba los goles y puntos de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
        
        clasificacion.clear();
        clasificacion = getClasificacionActual();
        
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            int posicion = 1;
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET posicion = " + posicion 
                    + " WHERE idEquipo = " + clasif.getIdEquipo());
                posicion++;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba las posiciones de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
        
    }
    
    /*
     * Devuelve la clasificación nueva después de calcular los nuevos goles y puntos.
     * Se diferencia con calculosPrevios1 y con calculosPrevios2 en que aquí ya se había calculado una clasificación anteriormente para una jornada.
     * Aquí calcula una clasificación al eliminar los goles y puntos de una jornada.
     */ 
    private List<Clasificacion> calculosPrevios3(final List<Jornada> jornadaAntes) {
     
        List<Clasificacion> clasificacionActual = new ArrayList<Clasificacion>();
        try {
            clasificacionActual = getClasificacionActual();
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras se obtenía la clasificación actual.", Notification.Type.ERROR_MESSAGE);
        }
            
            for(final Jornada jornadaAnterior : jornadaAntes){
            
                for(final Clasificacion clasificacion : clasificacionActual){
                    
                    if(clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoLocal()){
                        
                        clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                            - jornadaAnterior.getGolesEquipoLocal());
                        
                        clasificacion.setGolesContra(clasificacion.getGolesContra() 
                            - jornadaAnterior.getGolesEquipoVisitante());
                                
                        clasificacion.setDifGoles(clasificacion.getDifGoles() - jornadaAnterior.getGolesEquipoLocal() 
                            + jornadaAnterior.getGolesEquipoVisitante());
                        
                        if(jornadaAnterior.getGolesEquipoLocal() > jornadaAnterior.getGolesEquipoVisitante()){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 3);
                        }else if(jornadaAnterior.getGolesEquipoLocal() == jornadaAnterior.getGolesEquipoVisitante()){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 1);
                        }
                    }
                        
                    if(clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoVisitante()){
                        
                        clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                            - jornadaAnterior.getGolesEquipoVisitante());
                        
                        clasificacion.setGolesContra(clasificacion.getGolesContra() 
                            - jornadaAnterior.getGolesEquipoLocal());
                                
                        clasificacion.setDifGoles(clasificacion.getDifGoles() - jornadaAnterior.getGolesEquipoVisitante()
                            + jornadaAnterior.getGolesEquipoLocal());
                        
                        if(jornadaAnterior.getGolesEquipoLocal() < jornadaAnterior.getGolesEquipoVisitante()){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 3);
                        }else if(jornadaAnterior.getGolesEquipoLocal() == jornadaAnterior.getGolesEquipoVisitante()){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 1);
                        }
                    }
                }
            }
        
        return clasificacionActual;
        
    }
    
}
