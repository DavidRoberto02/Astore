package com.example.proyectoempresarial;

import java.io.Serializable;

public class listaClientes implements Serializable {
    public String nombre;
    public String color;


    public listaClientes(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

