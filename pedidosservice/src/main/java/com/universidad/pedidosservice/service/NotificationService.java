package com.universidad.pedidosservice.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void enviarEmail(String email) {

        System.out.println(
                "Enviando email a: " + email
        );
    }

    public void notificarUrgencia(boolean urgente) {

        System.out.println(
                "Pedido urgente: " + urgente
        );
    }
}