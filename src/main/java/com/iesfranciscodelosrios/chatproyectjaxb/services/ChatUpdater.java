package com.iesfranciscodelosrios.chatproyectjaxb.services;

import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.Chat;

import java.util.TimerTask;

public class ChatUpdater extends TimerTask {
    private Chat chat;

    public ChatUpdater(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        // Implementa la lógica de actualización del chat
        // Puede incluir la carga y guardado del chat
        chat.loadChat();
        // Realiza cualquier actualización necesaria
        // ...
        chat.saveChat();
    }
}
