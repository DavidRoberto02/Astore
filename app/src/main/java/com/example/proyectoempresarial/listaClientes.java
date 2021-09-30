package com.example.proyectoempresarial;

import java.io.Serializable;

public class listaClientes implements Serializable {
    private String nombre;
    private int numero;
    private String direccionCliente;

    public listaClientes(){

    }

    public listaClientes(String nombre, int numero, String direccionCliente) {
        this.nombre = nombre;
        this.numero = numero;
        this.direccionCliente = direccionCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
}

