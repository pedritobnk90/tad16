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
import tad.proyecto.entidades.Equipo;

/**
 *
 * @author PedroMadrigal
 */
public class EquiposDaoImpl implements EquiposDao {
    
    public List<Equipo> getEquipos() throws Exception{
        
        List<Equipo> equipos = new ArrayList<Equipo>();    
        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT * FROM Equipo");
            while(rs.next()){
                equipos.add(new Equipo(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        
        } catch (final Exception e){
            e.printStackTrace();
        }
        return equipos;
    }
    
    public Equipo getEquipo(final int id) throws Exception {
        
        Equipo equipo = new Equipo();    
        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT * FROM Equipo WHERE id = " + id);
            while(rs.next()){
                equipo.setId(rs.getInt(1));
                equipo.setNombre(rs.getString(2));
                equipo.setLiga(rs.getInt(3));
            }
        
        } catch (final Exception e){
            e.printStackTrace();
        }
        return equipo;
    }
    
    public Equipo getEquipo(final String nombre) throws Exception {
        
        Equipo equipo = new Equipo();    
        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT * FROM Equipo WHERE nombre = '" + nombre + "'");
            while(rs.next()){
                equipo.setId(rs.getInt(1));
                equipo.setNombre(rs.getString(2));
                equipo.setLiga(rs.getInt(3));
            }
        
        } catch (final Exception e){
            e.printStackTrace();
        }
        return equipo;
    }
    
}
