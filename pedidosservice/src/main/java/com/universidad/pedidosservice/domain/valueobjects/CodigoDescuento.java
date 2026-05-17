package com.universidad.pedidosservice.domain.valueobjects;

public class CodigoDescuento {

    private final String codigo;

    public CodigoDescuento(String codigo) {
        this.codigo = codigo;
    }

    public boolean esVip() {
        return "VIP10".equals(codigo);
    }

    public boolean esNuevo() {
        return "NEW20".equals(codigo);
    }
}