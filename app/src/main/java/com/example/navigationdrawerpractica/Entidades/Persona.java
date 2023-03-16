package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nit;
    private String nombre;
    private String direcion;
    private String telefonos;

    private String email;
    private String estado;
    private String cupo;
    private String descuento;
    private String condicion;
    private String lista_precios;
    private String lista_precios2;
    private String usado;
    private String notas;





    public Persona(){}



    public Persona(String nit, String nombre, String direccion,String telefonos,String email,String estado,String cupo,String descuento,String condicion, String lista_precios,String lista_precios2,String usado, String notas) {
        this.nombre = nombre;
        this.nit = nit;
        this.direcion = direccion;
        this.telefonos = telefonos;
        this.email = email;
        this.estado = estado;
        this.cupo = cupo;
        this.descuento = descuento;
        this.condicion = condicion;
        this.lista_precios = lista_precios;
        this.lista_precios2 = lista_precios2;
        this.usado = usado;
        this.notas = notas;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getLista_precios() {
        return lista_precios;
    }

    public void setLista_precios(String lista_precios) {
        this.lista_precios = lista_precios;
    }

    public String getLista_precios2() {
        return lista_precios2;
    }

    public void setLista_precios2(String lista_precios2) {
        this.lista_precios2 = lista_precios2;
    }

    public String getUsado() {
        return usado;
    }

    public void setUsado(String usado) {
        this.usado = usado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
