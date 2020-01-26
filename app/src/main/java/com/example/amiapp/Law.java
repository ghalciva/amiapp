package com.example.amiapp;

public class Law {

    private String id;
    private String estado;
    private String idperiodo;
    private String nombre;
    private String codDecreto;
    private String fechaPublicacion;
    private String descripcion;
    private String proponente;

    public Law() {
    }

    public Law(String id, String estado, String idperiodo, String nombre, String codDecreto, String fechaPublicacion, String descripcion, String proponente) {
        this.id = id;
        this.estado = estado;
        this.idperiodo = idperiodo;
        this.nombre = nombre;
        this.codDecreto = codDecreto;
        this.fechaPublicacion = fechaPublicacion;
        this.descripcion = descripcion;
        this.proponente = proponente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(String idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodDecreto() {
        return codDecreto;
    }

    public void setCodDecreto(String codDecreto) {
        this.codDecreto = codDecreto;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProponente() {
        return proponente;
    }

    public void setProponente(String proponente) {
        this.proponente = proponente;
    }
}

