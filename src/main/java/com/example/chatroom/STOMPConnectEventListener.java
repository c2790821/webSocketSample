package com.example.chatroom;

import com.example.chatroom.model.WebSocketSessions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(STOMPConnectEventListener.class);

    @Autowired
    WebSocketSessions sessions;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String user = accessor.getNativeHeader("user").get(0);
        String sessionId = accessor.getSessionId();
        sessions.registerSessionId(user, sessionId);
        LOG.info("user login, user:" + user + " sessionId:" + sessionId);
    }
}
