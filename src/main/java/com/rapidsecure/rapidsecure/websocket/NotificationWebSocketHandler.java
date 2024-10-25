package com.rapidsecure.rapidsecure.websocket;

import com.rapidsecure.rapidsecure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Autowired
    private PersonaRepository personaRepository; // Inyecta tu repositorio

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer rolId = extractRoleId(session); // Extrae el rol del token
        if (rolId != null && rolId.equals(2)) {
            sessions.add(session); // Solo agrega si rolId es 2
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    public void sendNotification(String notification) throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(notification));
            }
        }
    }

    private Integer extractRoleId(WebSocketSession session) {
        // Aquí implementa la lógica para obtener el rol del token
        return 2; // Simulación; reemplaza con lógica real
    }
}
