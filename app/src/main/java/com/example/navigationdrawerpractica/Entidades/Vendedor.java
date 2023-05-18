package com.example.navigationdrawerpractica.Entidades;

public class Vendedor {

    private static  String vendedor;
    private static  int ventas;


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

    public int getVenta() {
        return ventas;
    }
    public static double getVentas() {
        return ventas;
    }

    public static void setVentas(int ventas) {
        Vendedor.ventas = ventas;
    }
}
