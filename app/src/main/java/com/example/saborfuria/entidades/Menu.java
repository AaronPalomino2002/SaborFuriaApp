package com.example.saborfuria.entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Menu implements Serializable {

    private int id;

    private int foto;
    private String tipMenu, nomMen, desc;

    private double precio;

    private int cantidad;


    public Menu(int id, int foto, String tipMenu, String nomMen, String desc, double precio, int cantidad) {
        this.id = id;
        this.foto = foto;
        this.tipMenu = tipMenu;
        this.nomMen = nomMen;
        this.desc = desc;
        this.precio = precio;
        this.cantidad=cantidad;
    }

    public Menu(int foto,  String tipMenu, String nomMen, String desc, double precio, int cantidad) {
        this.foto = foto;
        this.cantidad = cantidad;
        this.tipMenu = tipMenu;
        this.nomMen = nomMen;
        this.desc = desc;
        this.precio = precio;
    }

    public Menu() {
        super();
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

    public String getTipMenu() {
        return tipMenu;
    }

    public void setTipMenu(String tipMenu) {
        this.tipMenu = tipMenu;
    }

    public String getNomMen() {
        return nomMen;
    }

    public void setNomMen(String nomMen) {
        this.nomMen = nomMen;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

