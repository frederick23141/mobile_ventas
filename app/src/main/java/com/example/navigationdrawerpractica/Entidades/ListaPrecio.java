package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class ListaPrecio implements Serializable {
    private String grupo;
    private String subgrupo;
    private String codigo;
    private String descripcion;
    private String distribuidor;
    private String mayorista;
    private String detallista;

    public ListaPrecio(){}

    public ListaPrecio(String grupo, String subgrupo, String codigo, String descripcion, String distribuidor, String mayorista, String detallista) {
        this.grupo = grupo;
        this.subgrupo = subgrupo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.distribuidor = distribuidor;
        this.mayorista = mayorista;
        this.detallista = detallista;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getMayorista() {
        return mayorista;
    }

    public void setMayorista(String mayorista) {
        this.mayorista = mayorista;
    }

    public String getDetallista() {
        return detallista;
    }

    public void setDetallista(String detallista) {
        this.detallista = detallista;
    }
}
