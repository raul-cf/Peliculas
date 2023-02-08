package com.cev.prueba.prueba.domain;

public class Cine implements Comparable<Cine> {
    String poblacion;
    String codigoPostal;
    String provincia;
    int precio;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public int compareTo(Cine otroCine) {
        return Integer.compare(otroCine.precio, this.precio);
    }

    @Override
    public String toString() {
        return "Pelicula [nombre=" + nombre + ", precio=" + precio + ", provincia=" + provincia + ", codigoPostal="
               + codigoPostal + ", poblacion=" + poblacion + "]";
    }

}