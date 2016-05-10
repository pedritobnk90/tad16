/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.entidades;

import java.io.Serializable;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int idJornada;
    private int idEquipoLocal;
    private int idEquipoVisitante;
    private int golesEquipoLocal;
    private int golesEquipoVisitante;

    //Variables útiles para join en la bbdd. Estas variables no se encuentran en la tabla Jornada
    private String nombreEquipoLocal;
    private String nombreEquipoVisitante;

    public Jornada() {
    }

    public Jornada(Integer id) {
        this.id = id;
    }

    public Jornada(Integer id, int idJornada, int idEquipoLocal, int idEquipoVisitante,
            int golesEquipoLocal, int golesEquipoVisitante) {
        this.id = id;
        this.idJornada = idJornada;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.golesEquipoLocal = golesEquipoLocal;
        this.golesEquipoVisitante = golesEquipoVisitante;
    }

    //Sobrecargo el constructor para las variables auxiliares de los nombres de los equipos
    public Jornada(Integer id, int idJornada, int idEquipoLocal, int idEquipoVisitante,
            int golesEquipoLocal, int golesEquipoVisitante, String nombreEquipoLocal,
            String nombreEquipoVisitante) {
        this.id = id;
        this.idJornada = idJornada;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.golesEquipoLocal = golesEquipoLocal;
        this.golesEquipoVisitante = golesEquipoVisitante;
        this.nombreEquipoLocal = nombreEquipoLocal;
        this.nombreEquipoVisitante = nombreEquipoVisitante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(int idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public int getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(int idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public int getGolesEquipoLocal() {
        return golesEquipoLocal;
    }

    public void setGolesEquipoLocal(int golesEquipoLocal) {
        this.golesEquipoLocal = golesEquipoLocal;
    }

    public int getGolesEquipoVisitante() {
        return golesEquipoVisitante;
    }

    public void setGolesEquipoVisitante(int golesEquipoVisitante) {
        this.golesEquipoVisitante = golesEquipoVisitante;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public String getNombreEquipoLocal() {
        return nombreEquipoLocal;
    }

    public void setNombreEquipoLocal(String nombreEquipoLocal) {
        this.nombreEquipoLocal = nombreEquipoLocal;
    }

    public String getNombreEquipoVisitante() {
        return nombreEquipoVisitante;
    }

    public void setNombreEquipoVisitante(String nombreEquipoVisitante) {
        this.nombreEquipoVisitante = nombreEquipoVisitante;
    }

}
