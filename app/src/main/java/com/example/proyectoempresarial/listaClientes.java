package com.example.proyectoempresarial;

import java.io.Serializable;

public class listaClientes implements Serializable {
    public String nombre;

    public listaClientes(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

