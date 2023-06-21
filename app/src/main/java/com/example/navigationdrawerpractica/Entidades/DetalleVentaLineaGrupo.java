package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class DetalleVentaLineaGrupo implements Serializable {
    private String grupo;


    public DetalleVentaLineaGrupo(){}


    public DetalleVentaLineaGrupo(String grupo) {
        this.grupo = grupo;

    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }


}
