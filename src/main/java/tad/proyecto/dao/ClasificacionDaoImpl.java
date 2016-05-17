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
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public class ClasificacionDaoImpl implements ClasificacionDao {
    
    /**
     * Este método se utiliza cuando se pretende añadir una NUEVA JORNADA.
     * @param jornadas Único parámetro del método.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    public void calculaClasificacion (final List<Jornada> jornadas) throws Exception {
        
        // Se calcula y obtiene la nueva clasificación con los resultados de la jornada.
        List<Clasificacion> clasificacion =  calculosPrevios(jornadas);
        
        // Se actualizan los registros de la clasificación.
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
        
        // Se obtiene la clasificación nueva desordenada para luego actualizar las posiciones.
        clasificacion.clear();
        clasificacion = getClasificacionActual();
        
        // Se actualizan las posiciones de los equipos ordenada por los puntos de cada equipo.
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

    /**
     * Este método se utiliza cuando se pretende añadir una NUEVA JORNADA.
     * @param jornadas Único parámetro del método.
     * @return List<Clasificacion> Devuelve la lista con la clasificación nueva tras la jornada pasada como parámetro.
     * @exception Exception
     * @see Exception
     */
    private List<Clasificacion> calculosPrevios(final List<Jornada> jornadas) {
     
        List<Clasificacion> clasificacionActual = new ArrayList<Clasificacion>();
        
        // Obtiene la clasificación actual ordenada por posición.
        try {
            clasificacionActual = getClasificacionActual();
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras se obtenía la clasificación actual.", Notification.Type.ERROR_MESSAGE);
        }
            
        // Recorremos los partidos de la jornada pasada como parámetro.
        for(final Jornada jornada : jornadas){
            
            // Recorremos la clasificación actual.
            for(final Clasificacion clasificacion : clasificacionActual){
                
                // Si el equipo jugó como local.
                if(clasificacion.getIdEquipo() == jornada.getIdEquipoLocal()){
                    clasificacion.setGolesFavor(clasificacion.getGolesFavor() + jornada.getGolesEquipoLocal());
                    clasificacion.setGolesContra(clasificacion.getGolesContra() + jornada.getGolesEquipoVisitante());
                    clasificacion.setDifGoles(clasificacion.getDifGoles() + jornada.getGolesEquipoLocal() 
                        - jornada.getGolesEquipoVisitante());
                    if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                        clasificacion.setPuntos(clasificacion.getPuntos() + 1);
                    }else if(jornada.getGolesEquipoLocal() > jornada.getGolesEquipoVisitante()){
                        clasificacion.setPuntos(clasificacion.getPuntos() + 3);
                    }
                    break;
                }
                // Si el equipo jugó como visitante.
                if(clasificacion.getIdEquipo() == jornada.getIdEquipoVisitante()){
                    clasificacion.setGolesFavor(clasificacion.getGolesFavor() + jornada.getGolesEquipoVisitante());
                    clasificacion.setGolesContra(clasificacion.getGolesContra() + jornada.getGolesEquipoLocal());
                    clasificacion.setDifGoles(clasificacion.getDifGoles() - jornada.getGolesEquipoLocal() 
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
    
    /**
     * Este método se utiliza para obtener la clasificación actual ordenadad por puntos.
     * @return List<Clasificacion> Devuelve la lista con la clasificación actual.
     * @exception Exception
     * @see Exception
     */
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
    
    /**
     * Este método se utiliza para actualizar la clasificación .
     * @param jornadaAntes Primer parámetro con los resultados de la jornada como estaban antes de ser actualizada
     * @param jornadas Segundo parámetro con los resultados de la jornada actualizados.
     * @return No devuelve nada.
     * @exception Exception
     * @see Exception
     */
    public void calculaClasificacion2 (final List<Jornada> jornadaAntes, final List<Jornada> jornadas) throws Exception {
        
        // Calcula la nueva clasificación actualizando los resultados de la jornada (actualizando goles y puntos).
        List<Clasificacion> clasificacion =  calculosPrevios2(jornadaAntes, jornadas);
        
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            
            // Actualiza los goles y los puntos.
            for(final Clasificacion clasif : clasificacion){
                s.executeUpdate("UPDATE Clasificacion SET golesFavor = " + clasif.getGolesFavor() + ", golesContra = " 
                    + clasif.getGolesContra() + ", difGoles = " + clasif.getDifGoles() + ", puntos = " 
                    + clasif.getPuntos() + " WHERE idEquipo = " + clasif.getIdEquipo());
            }
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras actualizaba los goles y puntos de la clasificación", Notification.Type.ERROR_MESSAGE);
        }
        
        // Se obtiene la clasificación nueva desordenada para luego actualizar las posiciones.
        clasificacion.clear();
        clasificacion = getClasificacionActual();
        
        // Actualiza las nuevas posiciones de la clasificación para los nuevos resultados de la jornada.
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
    
    /**
     * Este método se utiliza para calcular la nueva clasificación tras actualizar los goles y puntos.
     * @param jornadaAntes Primer parámetro con los resultados de la jornada como estaban antes de ser actualizada
     * @param jornadas Segundo parámetro con los resultados de la jornada actualizados.
     * @return List<Clasificacion> Devuelve la lista de la nueva clasificación.
     * @exception Exception
     * @see Exception
     */
    private List<Clasificacion> calculosPrevios2(final List<Jornada> jornadaAntes, final List<Jornada> jornadas) {
     
        // Obtenemos la clasificación antes de calcular los goles y puntos de cada equipo tras actualizar.
        List<Clasificacion> clasificacionActual = new ArrayList<Clasificacion>();
        try {
            clasificacionActual = getClasificacionActual();
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras se obtenía la clasificación actual.", Notification.Type.ERROR_MESSAGE);
        }
           
        // Recorremos los resultados de la jornada después de actualizarlos.
        for(final Jornada jornada : jornadas){
            
            // Recorremos los resultados de la jornada antes de actualizarlos.
            for(final Jornada jornadaAnterior : jornadaAntes){
            
                // Recorremos la clasificación actual para calcular los nuevos goles y puntos de cada equipo.
                for(final Clasificacion clasificacion : clasificacionActual){
                    
                    // Si el equipo ha jugado de local.
                    if(clasificacion.getIdEquipo() == jornada.getIdEquipoLocal() 
                        && clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoLocal()){
                        
                        // Si los goles del equipo local ha sido modificados.
                        if(jornada.getGolesEquipoLocal() != jornadaAnterior.getGolesEquipoLocal()){
                            
                            // Si los goles del equipo local se han disminuido.
                            if(jornadaAnterior.getGolesEquipoLocal() > jornada.getGolesEquipoLocal()){
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    - (jornadaAnterior.getGolesEquipoLocal() - jornada.getGolesEquipoLocal()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() - ((jornadaAnterior.getGolesEquipoLocal() 
                                    - jornadaAnterior.getGolesEquipoVisitante()) - (jornada.getGolesEquipoLocal() 
                                    - jornada.getGolesEquipoVisitante())));
                            }
                            // Si los goles del equipo local han aumentado.
                            else{
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    + (jornada.getGolesEquipoLocal() - jornadaAnterior.getGolesEquipoLocal()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() + ((jornada.getGolesEquipoLocal() 
                                    - jornada.getGolesEquipoVisitante()) - (jornadaAnterior.getGolesEquipoLocal() 
                                    - jornadaAnterior.getGolesEquipoVisitante())));
                            }
                        }
                        
                        // Si los goles del equipo visitante ha sido modificados.
                        if(jornada.getGolesEquipoVisitante() != jornadaAnterior.getGolesEquipoVisitante()){
                            
                            // Si los goles del equipo visitante se han disminuido.
                            if(jornadaAnterior.getGolesEquipoVisitante() > jornada.getGolesEquipoVisitante()){
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    - (jornadaAnterior.getGolesEquipoVisitante() - jornada.getGolesEquipoVisitante()));    
                            }
                            // Si los goles del equipo visitante se han aumentado.
                            else{
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    + (jornada.getGolesEquipoVisitante() - jornadaAnterior.getGolesEquipoVisitante()));
                            }
                        }
                        
                        // Si antes habíamos establecido que el partido terminó en empate.
                        if(jornadaAnterior.getGolesEquipoLocal() == jornadaAnterior.getGolesEquipoVisitante()){
                            
                            // Si ahora hemos establecido que el partido no ha terminado en empate.
                            if(jornada.getGolesEquipoLocal() != jornada.getGolesEquipoVisitante()){
                                
                                // Si hemos establecido que el equipo local ha ganado sumamos 2 puntos (1 antes con empate, 3 ahora con la victoria)
                                if(jornada.getGolesEquipoLocal() > jornada.getGolesEquipoVisitante()){
                                    clasificacion.setPuntos(clasificacion.getPuntos() + 2);
                                }
                                // Si hemos establecido que el equipo local había ganado antes, restamos 2 puntos (3 antes con victoria, 1 ahora con la empate)
                                else{
                                    clasificacion.setPuntos(clasificacion.getPuntos() - 2);
                                }
                            }
                        }
                        // Si antes el equipo local ganó el partido.
                        else if(jornadaAnterior.getGolesEquipoLocal() > jornadaAnterior.getGolesEquipoVisitante()){
                            
                            // Si ahora el partido ha terminado empate restamos dos puntos.
                            if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 2);
                            } 
                            // Si ahora quien ha ganado el partido ha sido el equipo visitante restamos tres puntos.
                            else if(jornada.getGolesEquipoLocal() < jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 3);
                            }
                        }
                        // Si antes el equipo visitante ganó el partido.
                        else{
                            
                            // Si ahora el partido ha terminado el empate sumamos un punto.
                            if(jornada.getGolesEquipoLocal() == jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 1);
                            } 
                            // Si ahora quien ha ganado el partido ha sido el equipo local sumamos tres puntos.
                            else if(jornada.getGolesEquipoLocal() > jornada.getGolesEquipoVisitante()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 3);
                            }
                        }
                        break;
                    }
                    
                    // Si el equipo ha jugado de visitante.
                    if(clasificacion.getIdEquipo() == jornada.getIdEquipoVisitante()
                        && clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoVisitante()){
                        
                        // Si los goles del equipo visitante ha sido modificados.
                        if(jornada.getGolesEquipoVisitante() != jornadaAnterior.getGolesEquipoVisitante()){
                            
                            // Si los goles han sido disminuidos.
                            if(jornadaAnterior.getGolesEquipoVisitante() > jornada.getGolesEquipoVisitante()){
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    - (jornadaAnterior.getGolesEquipoVisitante() - jornada.getGolesEquipoVisitante()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() - ((jornadaAnterior.getGolesEquipoVisitante()
                                    - jornadaAnterior.getGolesEquipoLocal()) - (jornada.getGolesEquipoVisitante()
                                    - jornada.getGolesEquipoLocal())));
                            }
                            // Si los goles han sido aumentados.
                            else{
                                clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                                    + (jornada.getGolesEquipoVisitante() - jornadaAnterior.getGolesEquipoVisitante()));
                                
                                clasificacion.setDifGoles(clasificacion.getDifGoles() + ((jornada.getGolesEquipoVisitante()
                                    - jornada.getGolesEquipoLocal()) - (jornadaAnterior.getGolesEquipoVisitante()
                                    - jornadaAnterior.getGolesEquipoLocal())));
                            }
                        }
                        
                        // Si los goles del equipo local han sido modificados.
                        if(jornada.getGolesEquipoLocal() != jornadaAnterior.getGolesEquipoLocal()){
                            
                            // Si los goles del equipo local han sido disminuidos.
                            if(jornadaAnterior.getGolesEquipoLocal() > jornada.getGolesEquipoLocal()){
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    - (jornadaAnterior.getGolesEquipoLocal() - jornada.getGolesEquipoLocal()));
                                
                            }
                            // Si los goles del equipo local han sido aumentados.
                            else{
                                clasificacion.setGolesContra(clasificacion.getGolesContra()
                                    + (jornada.getGolesEquipoLocal() - jornadaAnterior.getGolesEquipoLocal()));
                                
                            }
                        }
                        
                        // Si antes habíamos establecido que el partido terminó en empate.
                        if(jornadaAnterior.getGolesEquipoVisitante() == jornadaAnterior.getGolesEquipoLocal()){
                            
                            // Si ahora el empate no ha terminado en empate.
                            if(jornada.getGolesEquipoVisitante() != jornada.getGolesEquipoLocal()){
                                
                                // Si ahora el partido lo ha ganado el equipo visitante.
                                if(jornada.getGolesEquipoVisitante() > jornada.getGolesEquipoLocal()){
                                    clasificacion.setPuntos(clasificacion.getPuntos() + 2);
                                }
                                // Si ahora el partido lo ha ganado el equipo local.
                                else{
                                    clasificacion.setPuntos(clasificacion.getPuntos() - 1);
                                }
                            }
                        }
                        // Si antes habíamos establecido que el partido lo había ganado el equipo visitante.
                        else if(jornadaAnterior.getGolesEquipoVisitante() > jornadaAnterior.getGolesEquipoLocal()){
                            
                            // Si ahora el partido ha terminado en empate.
                            if(jornada.getGolesEquipoVisitante() == jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 2);
                            } 
                            // Si ahora el partido lo ha ganado el equipo local.
                            else if(jornada.getGolesEquipoVisitante() < jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() - 3);
                            }
                        }
                        // Si antes habíamos establecido que el partido lo había ganado el equipo local.
                        else{
                            
                            // Si ahora el partido ha sido empate.
                            if(jornada.getGolesEquipoVisitante() == jornada.getGolesEquipoLocal()){
                                clasificacion.setPosicion(clasificacion.getPuntos() + 1);
                            } 
                            // Si ahora el partido lo ha ganado el equipo visitante.
                            else if(jornada.getGolesEquipoVisitante() > jornada.getGolesEquipoLocal()){
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
    
    /**
     * Este método se utiliza para actualizar la clasificación tras eliminar una jornada.
     * @param jornadaAntes Primer parámetro con los resultados de la jornada como estaban antes de ser actualizada
     * @return No devuelve nada.
     * @exception Exception
     * @see Exception
     */
    public void calculaClasificacion3 (final List<Jornada> jornadaAntes) throws Exception {
        
        // Calcula la nueva clasificación actualizando los resultados de la jornada a NULL (actualizando goles y puntos).
        List<Clasificacion> clasificacion =  calculosPrevios3(jornadaAntes);
        
        // Actualiza los nuevos goles de los equipos y los puntos.
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
        
        // Obtenemos la clasificación actual sin actualizar las posiciones y ordenada por puntos.
        clasificacion.clear();
        clasificacion = getClasificacionActual();
        
        // Actualizamos las posiciones de los equipos.
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
    
    /**
     * Este método se utiliza para calcular la nueva clasificación tras actualizar los goles y puntos tras eliminar una jornada.
     * @param jornadaAntes Primer parámetro con los resultados de la jornada como estaban antes de ser actualizada
     * @return List<Clasificacion> Devuelve la lista de la nueva clasificación.
     * @exception Exception
     * @see Exception
     */
    private List<Clasificacion> calculosPrevios3(final List<Jornada> jornadaAntes) {
     
        // Obtenemos la clasificación actual antes de actualizar goles y puntos.
        List<Clasificacion> clasificacionActual = new ArrayList<Clasificacion>();
        try {
            clasificacionActual = getClasificacionActual();
        } catch(final Exception e) {
            e.printStackTrace();
            Notification.show("Ocurrió un error mientras se obtenía la clasificación actual.", Notification.Type.ERROR_MESSAGE);
        }
            
        // Recorremos la jornada anterior, los goles de cada equipo.
        for(final Jornada jornadaAnterior : jornadaAntes){
        
            // Recorremos la clasificación que tenemos actualmente antes de actualizar.
            for(final Clasificacion clasificacion : clasificacionActual){
                
                // Si el equipo ha jugado como local.
                if(clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoLocal()){
                    
                    clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                        - jornadaAnterior.getGolesEquipoLocal());
                    
                    clasificacion.setGolesContra(clasificacion.getGolesContra() 
                        - jornadaAnterior.getGolesEquipoVisitante());
                            
                    clasificacion.setDifGoles(clasificacion.getDifGoles() - (jornadaAnterior.getGolesEquipoLocal() 
                        - jornadaAnterior.getGolesEquipoVisitante()));
                    
                    // Si el equipo local había ganado el partido, se le restan los tres puntos que entonces tenía.
                    if(jornadaAnterior.getGolesEquipoLocal() > jornadaAnterior.getGolesEquipoVisitante()){
                        
                        //Si tiene 0 puntos actualmente, no se le restan
                        if(clasificacion.getPuntos() > 0){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 3);
                        }
                    }
                    // Si el equipo visitante había ganado el partido, se le suman el punto que entonces tenía.
                    else if(jornadaAnterior.getGolesEquipoLocal() == jornadaAnterior.getGolesEquipoVisitante()){
                        
                        //Si tiene 0 puntos actualmente, no se le restan
                        if(clasificacion.getPuntos() > 0){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 1);
                        }
                    }
                }
                
                // Si el equipo ha jugado como visitante.
                if(clasificacion.getIdEquipo() == jornadaAnterior.getIdEquipoVisitante()){
                    
                    clasificacion.setGolesFavor(clasificacion.getGolesFavor() 
                        - jornadaAnterior.getGolesEquipoVisitante());
                    
                    clasificacion.setGolesContra(clasificacion.getGolesContra() 
                        - jornadaAnterior.getGolesEquipoLocal());
                            
                    clasificacion.setDifGoles(clasificacion.getDifGoles() - (jornadaAnterior.getGolesEquipoVisitante()
                        - jornadaAnterior.getGolesEquipoLocal()));
                    
                    // Si el equipo visitante había ganado el partido.
                    if(jornadaAnterior.getGolesEquipoLocal() < jornadaAnterior.getGolesEquipoVisitante()){
                        
                        //Si tiene 0 puntos actualmente, no se le restan
                        if(clasificacion.getPuntos() > 0){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 3);
                        }
                    }
                    // Si el partido había terminado en empate, se le resta un punto
                    else if(jornadaAnterior.getGolesEquipoLocal() == jornadaAnterior.getGolesEquipoVisitante()){
                        
                        //Si tiene 0 puntos actualmente, no se le restan
                        if(clasificacion.getPuntos() > 0){
                            clasificacion.setPuntos(clasificacion.getPuntos() - 1);
                        }
                    }
                }
            }
        }
        return clasificacionActual;
    }
    
    /**
     * Este método se utiliza para obtener la clasificación con los nombres de los equipos actual ordenada por puntos.
     * @return List<Clasificacion> Devuelve la lista con la clasificación actual.
     * @exception Exception
     * @see Exception
     */
    public List<Clasificacion> getClasificacionActualEquipos() throws Exception {
        
        List<Clasificacion> clasificacion = new ArrayList<Clasificacion>();
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT c.golesFavor, c.golesContra, c.difGoles, e.nombre "
                    + "FROM clasificacion c LEFT JOIN equipo e ON (c.idEquipo = e.id) WHERE c.idLiga = 1");
            while(res.next()) {
                clasificacion.add(new Clasificacion(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4)));
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return clasificacion;
    }
    
    /**
     * Este método se utiliza para obtener el menor número de goles en contra.
     * @return int Devuelve el número mínimo de goles en contra.
     * @exception Exception
     * @see Exception
     */
    public int getMinGolesContra() throws Exception {
        
        int minGolesContra = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT MIN(golesContra) FROM clasificacion WHERE idLiga = 1");
            while(res.next()) {
                minGolesContra = res.getInt(1);
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return minGolesContra;
    }
    
    /**
     * Este método se utiliza para obtener el mayor número de goles en contra.
     * @return int Devuelve el número máximo de goles en contra.
     * @exception Exception
     * @see Exception
     */
    public int getMaxGolesContra() throws Exception {
        
        int maxGolesContra = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT MAX(golesContra) FROM clasificacion WHERE idLiga = 1");
            while(res.next()) {
                maxGolesContra = res.getInt(1);
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return maxGolesContra;
    }
    
    /**
     * Este método se utiliza para obtener el menor número de goles a favor.
     * @return int Devuelve el número mínimo de goles en contra.
     * @exception Exception
     * @see Exception
     */
    public int getMinGolesFavor() throws Exception {
        
        int minGolesFavor = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT MIN(golesFavor) FROM clasificacion WHERE idLiga = 1");
            while(res.next()) {
                minGolesFavor = res.getInt(1);
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return minGolesFavor;
    }
    
    /**
     * Este método se utiliza para obtener el mayor número de goles a favor.
     * @return int Devuelve el número máximo de goles a favor.
     * @exception Exception
     * @see Exception
     */
    public int getMaxGolesFavor() throws Exception {
        
        int maxGolesFavor = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT MAX(golesFavor) FROM clasificacion WHERE idLiga = 1");
            while(res.next()) {
                maxGolesFavor = res.getInt(1);
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return maxGolesFavor;
    }
    
    /**
     * Este método se utiliza para obtener el menor número de diferencia de goles.
     * @return int Devuelve el número mínimo de diferencia de goles.
     * @exception Exception
     * @see Exception
     */
    public int getMinDifGoles() throws Exception {
        
        int minDifGoles = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT MIN(difGoles) FROM clasificacion WHERE idLiga = 1");
            while(res.next()) {
                minDifGoles = res.getInt(1);
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return minDifGoles;
    }
    
    /**
     * Este método se utiliza para obtener el mayor número de diferencia de goles.
     * @return int Devuelve el número máximo de diferencia de goles.
     * @exception Exception
     * @see Exception
     */
    public int getMaxDifGoles() throws Exception {
        
        int maxDifGoles = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery ("SELECT MAX(difGoles) FROM clasificacion WHERE idLiga = 1");
            while(res.next()) {
                maxDifGoles = res.getInt(1);
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return maxDifGoles;
    }
    
}
