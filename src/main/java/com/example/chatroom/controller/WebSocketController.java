package com.example.chatroom.controller;


import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/WebSocketServer/{usernick}")
public class WebSocketController {

    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam(value = "usernick") String userNick, Session session) {
        String message = "有新成員[" + userNick + "]加入聊天室!";
        System.out.println(message);
        ONLINE_SESSION.put(userNick, session);
        sendMessageForAll(message);
    }

    private void sendMessageForAll(String message) {
        ONLINE_SESSION.forEach(((s, session) -> sendMessage(session, message)));

    }

    private void sendMessage(Session session, String message) {
        if (Objects.isNull(session))
            return;
        RemoteEndpoint.Async asyncRemote = session.getAsyncRemote();
        asyncRemote.sendText(message);
    }
}
