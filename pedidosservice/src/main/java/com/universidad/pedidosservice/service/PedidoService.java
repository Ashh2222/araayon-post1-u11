package com.universidad.pedidosservice.service;

import com.universidad.pedidosservice.domain.Pedido;
import com.universidad.pedidosservice.domain.Producto;
import com.universidad.pedidosservice.domain.valueobjects.CodigoDescuento;
import com.universidad.pedidosservice.domain.valueobjects.DatosCliente;
import com.universidad.pedidosservice.domain.valueobjects.LineaPedido;
import com.universidad.pedidosservice.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final NotificationService notificationService;

    public PedidoService(
            PedidoRepository repo,
            NotificationService notificationService
    ) {

        this.repo = repo;
        this.notificationService = notificationService;
    }

    public String procesarPedido(
            DatosCliente cliente,
            List<LineaPedido> lineas,
            boolean esUrgente,
            CodigoDescuento descuento
    ) {

        validarCliente(cliente);

        Pedido pedido = crearPedido(
                cliente,
                lineas,
                descuento
        );

        enviarNotificaciones(
                cliente,
                esUrgente
        );

        return generarRespuesta(
                repo.save(pedido)
        );
    }

    private void validarCliente(
            DatosCliente cliente
    ) {

        if (cliente.getId() == null
                || cliente.getNombre() == null
                || cliente.getNombre().isBlank()
                || cliente.getEmail() == null
                || !cliente.getEmail().contains("@")) {

            throw new IllegalArgumentException(
                    "Cliente inválido"
            );
        }
    }

    private Pedido crearPedido(
            DatosCliente cliente,
            List<LineaPedido> lineas,
            CodigoDescuento descuento
    ) {

        double total = calcularTotal(
                lineas,
                descuento
        );

        return new Pedido(
                cliente.getId(),
                cliente.getNombre(),
                total
        );
    }

    private double calcularTotal(
            List<LineaPedido> lineas,
            CodigoDescuento descuento
    ) {

        double total = 0;

        for (LineaPedido linea : lineas) {

            Producto producto =
                    repo.findProductoById(
                            linea.getProductoId()
                    );

            if (producto == null) {

                throw new IllegalArgumentException(
                        "Producto inválido"
                );
            }

            total += producto.getPrecio()
                    * linea.getCantidad();
        }

        return aplicarDescuento(
                total,
                descuento
        );
    }

    private double aplicarDescuento(
            double total,
            CodigoDescuento descuento
    ) {

        if (descuento == null) {
            return total;
        }

        if (descuento.esVip()) {
            return total * 0.90;
        }

        if (descuento.esNuevo()) {
            return total * 0.80;
        }

        return total;
    }

    private void enviarNotificaciones(
            DatosCliente cliente,
            boolean esUrgente
    ) {

        notificationService.enviarEmail(
                cliente.getEmail()
        );

        notificationService.notificarUrgencia(
                esUrgente
        );
    }

    private String generarRespuesta(
            Pedido pedido
    ) {

        return "OK_" + pedido.getId();
    }
}