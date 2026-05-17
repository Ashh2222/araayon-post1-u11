package com.universidad.pedidosservice.repository;

import com.universidad.pedidosservice.domain.Pedido;
import com.universidad.pedidosservice.domain.Producto;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository {

    public Producto findProductoById(Long id) {
        return new Producto(id, "Producto", 100);
    }

    public Pedido save(Pedido pedido) {
        return pedido;
    }
}