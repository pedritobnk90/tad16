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
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private Integer liga;

    public Equipo() {
    }

    public Equipo(Integer id) {
        this.id = id;
    }

    public Equipo(Integer id, String nombre, Integer liga) {
        this.id = id;
        this.nombre = nombre;
        this.liga = liga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getLiga() {
        return liga;
    }

    public void setLiga(Integer liga) {
        this.liga = liga;
    }

}
