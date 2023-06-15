package com.example.navigationdrawerpractica.Interfaces;

import com.example.navigationdrawerpractica.Entidades.ListaPrecio;

public interface iComunicaListaPerciosFragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de personas y el detalle
    public void enviarListaPrecio(ListaPrecio listaPrecio); //se transportara un objeto de tipo persona
    //(En la clase Persona se implementa Serializable para poder transportar un objeteo a otro)
}
