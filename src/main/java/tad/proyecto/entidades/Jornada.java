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
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int idEquipoLocal;
    private int idEquipoVisitante;
    private int golesEquipoLocal;
    private int golesEquipoVisitante;

    public Jornada() {
    }

    public Jornada(Integer id) {
        this.id = id;
    }

    public Jornada(Integer id, int idEquipoLocal, int idEquipoVisitante, int golesEquipoLocal, int golesEquipoVisitante) {
        this.id = id;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.golesEquipoLocal = golesEquipoLocal;
        this.golesEquipoVisitante = golesEquipoVisitante;
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

}
