package com.example.saborfuria.entidades;

import java.io.Serializable;

public class Productos implements Serializable {

    private int id;

    private int foto;
    private String tipoProducto, nomProducto, descriProducto;
    private int cantidadProducto;
    private double precioProducto;

    public Productos(int id, int foto, String tipoProducto, String nomProducto, String descriProducto, int cantidadProducto, double precioProducto) {
        this.id = id;
        this.foto = foto;
        this.tipoProducto = tipoProducto;
        this.nomProducto = nomProducto;
        this.descriProducto = descriProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
    }

    public Productos(int foto, String tipoProducto, String nomProducto, String descriProducto, int cantidadProducto, double precioProducto) {
        this.foto = foto;
        this.tipoProducto = tipoProducto;
        this.nomProducto = nomProducto;
        this.descriProducto = descriProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
    }

    public Productos() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getDescriProducto() {
        return descriProducto;
    }

    public void setDescriProducto(String descriProducto) {
        this.descriProducto = descriProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }
}
