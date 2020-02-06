package com.example.amiapp;

public class Citizen {

    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String contrasena;

    public Citizen() {
    }

    public Citizen(String id, String nombre, String apellido, String cedula, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public String getId(String string) {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre(String nombre) {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido(String descripcion) {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula(String fecha_publicacion) {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena(String cod_decreto) {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
