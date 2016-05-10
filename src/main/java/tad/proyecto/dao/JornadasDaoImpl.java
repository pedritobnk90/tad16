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
 *
 * @author PedroMadrigal
 */
public class JornadasDaoImpl implements JornadasDao{
    
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
