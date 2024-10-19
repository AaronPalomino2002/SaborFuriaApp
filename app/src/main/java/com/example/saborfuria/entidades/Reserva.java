package com.example.saborfuria.entidades;

import java.io.Serializable;

public class Reserva implements Serializable {

    private int id;

    private String nom,ape;
    private String numMesa, cantPer,fecRes, horares;

    public Reserva(int id, String nom, String ape, String numMesa, String cantPer, String fecRes, String horares) {
        this.id=id;
        this.nom = nom;
        this.ape = ape;
        this.numMesa = numMesa;
        this.cantPer = cantPer;
        this.fecRes = fecRes;
        this.horares = horares;
    }

    public Reserva(String nom, String ape, String numMesa, String cantPer, String fecRes, String horares) {
        this.nom = nom;
        this.ape = ape;
        this.numMesa = numMesa;
        this.cantPer = cantPer;
        this.fecRes = fecRes;
        this.horares = horares;
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

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(String numMesa) {
        this.numMesa = numMesa;
    }

    public String getCantPer() {
        return cantPer;
    }

    public void setCantPer(String cantPer) {
        this.cantPer = cantPer;
    }

    public String getFecRes() {
        return fecRes;
    }

    public void setFecRes(String fecRes) {
        this.fecRes = fecRes;
    }

    public String getHorares() {
        return horares;
    }

    public void setHorares(String horares) {
        this.horares = horares;
    }
}
