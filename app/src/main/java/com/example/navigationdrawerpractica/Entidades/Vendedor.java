package com.example.navigationdrawerpractica.Entidades;

public class Vendedor {

    private static  String vendedor;
    private static  String ventas;


    public String getVendedor() {
        return vendedor;
    }

    public Vendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Vendedor() {
        this.vendedor = vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public static String getVentas() {
        return ventas;
    }

    public static void setVentas(String ventas) {
        Vendedor.ventas = ventas;
    }
}
