package com.example.chatroom.model;

import java.io.Serializable;

public class ChatClientModel implements Serializable {
    private String clientName;


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
