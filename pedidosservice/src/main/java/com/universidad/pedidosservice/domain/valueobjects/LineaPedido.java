package com.universidad.pedidosservice.domain.valueobjects;

public class LineaPedido {

    private final Long productoId;
    private final Integer cantidad;

    public LineaPedido(
            Long productoId,
            Integer cantidad
    ) {

        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getProductoId() {
        return productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}