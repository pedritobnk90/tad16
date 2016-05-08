/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.entidades;

import java.io.Serializable;

/**
 *
 * @author PedroMadrigal
 */
public class Clasificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int idLiga;
    private int idEquipo;
    private int posicion;
    private int puntos; 
    private int golesFavor;
    private int golesContra;
    private int difGoles;

    public Clasificacion() {
    }

    public Clasificacion(Integer id) {
        this.id = id;
    }

    public Clasificacion(Integer id, int idLiga, int idEquipo, int posicion, int puntos, int golesFavor, int golesContra, int difGoles) {
        this.id = id;
        this.idLiga = idLiga;
        this.idEquipo = idEquipo;
        this.posicion = posicion;
        this.puntos = puntos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.difGoles = difGoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getDifGoles() {
        return difGoles;
    }

    public void setDifGoles(int difGoles) {
        this.difGoles = difGoles;
    }
    
    public int compareTo(final Clasificacion clasificacion) {
        if (puntos < clasificacion.puntos) {
            return -1;
        }
        if (puntos > clasificacion.puntos) {
            return 1;
        }
        return 0;
    }

}
