package com.example.saborfuria.entidades;
import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;

    private int foto;
    private String nombres, apellidos, distrito, direccion, correo, celular, contraseña;
    private int edad;

    public Cliente(int id, int foto, String nombres, String apellidos, String distrito, String direccion, String correo, String celular, String contraseña, int edad) {
        this.id = id;
        this.foto = foto;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.distrito = distrito;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
        this.contraseña = contraseña;
        this.edad = edad;
    }

    public Cliente(int foto, String nombres, String apellidos, String distrito, String direccion, String correo, String celular, String contraseña, int edad) {
        this.foto = foto;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.distrito = distrito;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
        this.contraseña = contraseña;
        this.edad = edad;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}