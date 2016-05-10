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
import java.util.ArrayList;
import java.util.List;
import tad.proyecto.entidades.Jornada;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public class JornadasDaoImpl implements JornadasDao{
    
    /**
     * Este método se utiliza para obtener una jornada con todos sus resultados
     * @param idJornada Único parámetro del método, el número de jornada.
     * @return List<Jornada> Devuelve la lista de resultados de una jornada.
     * @exception Exception
     * @see Exception
     */
    public List<Jornada> getJornada (final int idJornada) throws Exception {
     
        List<Jornada> jornadas = new ArrayList<Jornada>();
        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT j.id, j.idJornada, j.idEquipoLocal, j.idEquipoVisitante,"
                    + " j.golesEquipoLocal, j.golesEquipoVisitante, e.nombre, e2.nombre FROM Jornada j LEFT JOIN"
                    + " Equipo e ON (j.idEquipoLocal = e.id) LEFT JOIN Equipo e2 ON (j.idEquipoVisitante = e2.id)"
                    + " WHERE idJornada =" + idJornada);
            while(rs.next()){
                jornadas.add(new Jornada(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                    rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
            }
        } catch (final Exception e){
            e.printStackTrace();
        }
        return jornadas;
    }
    
    /**
     * Este método se utiliza para obtener las jornadas que ya han sido añadidos sus resultados.
     * @return List<Integer> Devuelve la lista de enteros de las jornadas no añadidas aún.
     * @exception Exception
     * @see Exception
     */
    public List<Integer> getJornadasTerminadas() throws Exception {
        
        List<Integer> jornadas = new ArrayList<Integer>();
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT DISTINCT(idJornada) FROM Jornada WHERE golesEquipoLocal IS NOT NULL");
            while(rs.next()){
                jornadas.add(rs.getInt(1));
            }
        } catch (final Exception e){
            e.printStackTrace();
        }
        return jornadas;
        
    }
    
    /**
     * Este método se utiliza para obtener las jornadas que ya han sido añadidos sus resultados.
     * @return List<Jornada> Devuelve la lista de jornadas completas e incompletas.
     * @exception Exception
     * @see Exception
     */
    public List<Jornada> getJornadas() throws Exception {
        
        List<Jornada> jornadas = new ArrayList<Jornada>();    
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT * FROM Jornada");
            while(rs.next()){
                jornadas.add(new Jornada(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                    rs.getInt(5), rs.getInt(6)));
            }
        } catch (final Exception e){
            e.printStackTrace();
        }
        return jornadas;
    }
    
    /**
     * Este método se utiliza para actualizar una jornada (sus resultados)
     * @param List<Jornada> Primer parámetro, una lista de resultados de una jornada.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    public void updateJornadas(final List<Jornada> jornadas) throws Exception {
        
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            for(final Jornada jornada : jornadas){
                s.executeUpdate("UPDATE Jornada SET golesEquipoLocal = " + jornada.getGolesEquipoLocal()
                    + ", golesEquipoVisitante = " + jornada.getGolesEquipoVisitante()
                    + " WHERE idJornada = " + jornada.getIdJornada() + " AND idEquipoLocal = " + jornada.getIdEquipoLocal() 
                    + " AND idEquipoVisitante = " + jornada.getIdEquipoVisitante());
            }
        } catch (final Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Este método se utiliza para obtener el número de la siguiente jornada.
     * @return int Devuelve el número de la siguiente jornada.
     * @exception Exception
     * @see Exception
     */
    public int getSiguienteJornada() throws Exception {
        int idJornada = 0;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet res = s.executeQuery("SELECT idJornada FROM Jornada WHERE golesEquipoLocal IS NULL LIMIT 1");
            while(res.next()){
                idJornada = res.getInt(1);
            }
        } catch (final Exception e){
            e.printStackTrace();
        }
        return idJornada;
    }
    
    /**
     * Este método se utiliza para borrar una jornada (sus resultados)
     * @param List<Jornada> Primer parámetro, una lista de resultados de una jornada.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    public void borrarJornadas(final List<Jornada> jornadas) throws Exception {
        
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            for(final Jornada jornada : jornadas){
                s.executeUpdate("UPDATE Jornada SET golesEquipoLocal = NULL, "
                    + "golesEquipoVisitante = NULL WHERE idJornada = " + jornada.getIdJornada() 
                    + " AND idEquipoLocal = " + jornada.getIdEquipoLocal() 
                    + " AND idEquipoVisitante = " + jornada.getIdEquipoVisitante());
            }
        } catch (final Exception e){
            e.printStackTrace();
        }
    }
    
}
