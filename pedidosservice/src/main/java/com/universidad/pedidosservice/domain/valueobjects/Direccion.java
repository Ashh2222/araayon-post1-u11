package com.universidad.pedidosservice.domain.valueobjects;

public class Direccion {

    private final String direccion;
    private final String ciudad;
    private final String codigoPostal;

    public Direccion(
            String direccion,
            String ciudad,
            String codigoPostal
    ) {

        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }
}