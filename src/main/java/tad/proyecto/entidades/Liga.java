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
public class Liga implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int idCategoria;
    private String desCategoria;

    public Liga() {
    }

    public Liga(Integer id) {
        this.id = id;
    }

    public Liga(Integer id, int idCategoria, String desCategoria) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.desCategoria = desCategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDesCategoria() {
        return desCategoria;
    }

    public void setDesCategoria(String desCategoria) {
        this.desCategoria = desCategoria;
    }

}
