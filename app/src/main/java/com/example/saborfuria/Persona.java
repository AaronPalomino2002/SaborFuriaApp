package com.example.saborfuria;

public class Persona {

    int id;
    String nom,cel,ema,pro;
    int idImg;

    public Persona(int id, String nom, String cel, String ema, String pro, int idImg) {
        this.id = id;
        this.nom = nom;
        this.cel = cel;
        this.ema = ema;
        this.pro = pro;
        this.idImg = idImg;
    }

    public Persona() {

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

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEma() {
        return ema;
    }

    public void setEma(String ema) {
        this.ema = ema;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }
}
