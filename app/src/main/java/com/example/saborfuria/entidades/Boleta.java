package com.example.saborfuria.entidades;

import java.io.Serializable;

public class Boleta implements Serializable {

    private int id;

    private String nom, celular, direccion, metPag;

    private Double total;

    public Boleta(int id, String nom, String celular, String direccion, String metPag, Double total) {
        this.id = id;
        this.nom = nom;
        this.celular = celular;
        this.direccion = direccion;
        this.metPag = metPag;
        this.total = total;
    }

    public Boleta(String nom, String celular, String direccion, String metPag, Double total) {
        this.nom = nom;
        this.celular = celular;
        this.direccion = direccion;
        this.metPag = metPag;
        this.total = total;
    }

    public Boleta() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetPag() {
        return metPag;
    }

    public void setMetPag(String metPag) {
        this.metPag = metPag;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
