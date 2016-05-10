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
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String pass;
    private String nombre;
    private String apellidos;
    private int equipoFavorito;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String username, String pass, String nombre, String apellidos, int equipoFavorito) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.equipoFavorito = equipoFavorito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEquipoFavorito() {
        return equipoFavorito;
    }

    public void setEquipoFavorito(int equipoFavorito) {
        this.equipoFavorito = equipoFavorito;
    }

}
