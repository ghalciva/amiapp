package com.example.amiapp;

public class Articles {

    private String id;
    private String name;
    private String detalle;
    private String resumen;
    private String expArticulo;
    private String idley;

    public Articles() {
    }

    public Articles(String id, String name, String detalle, String resumen, String expArticulo, String idley) {
        this.id = id;
        this.name = name;
        this.detalle = detalle;
        this.resumen = resumen;
        this.expArticulo = expArticulo;
        this.idley = idley;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getExpArticulo() {
        return expArticulo;
    }

    public void setExpArticulo(String expArticulo) {
        this.expArticulo = expArticulo;
    }

    public String getIdley() {
        return idley;
    }

    public void setIdley(String idley) {
        this.idley = idley;
    }
}
