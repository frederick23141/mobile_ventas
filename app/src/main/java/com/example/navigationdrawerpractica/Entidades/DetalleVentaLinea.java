package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class DetalleVentaLinea implements Serializable {
    private String grupo;
    private String subgrupo;
    private Integer kilos;
    private Integer kilosp;
    private Integer pesos;
    private Integer pesosp;


    public DetalleVentaLinea(){}



    public DetalleVentaLinea(String grupo, String subgrupo, int kilos  , int pesos, int kilosp, int pesosp) {
        this.grupo = grupo;
        this.subgrupo = subgrupo;
        this.kilos = kilos;
        this.pesos = pesos;
        this.kilosp = kilosp;
        this.pesosp = pesosp;
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

    public Integer getKilos() {
        return kilos;
    }

    public void setKilos(Integer kilos) {
        this.kilos = kilos;
    }

    public Integer getPesos() {
        return pesos;
    }

    public void setPesos(Integer pesos) {
        this.pesos = pesos;
    }
    public Integer getKilosp() {
        return kilosp;
    }

    public void setKilosp(Integer kilosp) {
        this.kilosp = kilosp;
    }

    public Integer getPesosp() {
        return pesosp;
    }

    public void setPesosp(Integer pesosp) {
        this.pesosp = pesosp;
    }

}
