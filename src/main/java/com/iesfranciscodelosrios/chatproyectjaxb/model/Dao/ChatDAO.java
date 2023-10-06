package com.iesfranciscodelosrios.chatproyectjaxb.model.Dao;


import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.Chat;
import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.Message;
import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.User;
import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.Users;

import java.util.Date;

public class ChatDAO {
    public Chat createChat(String chatname) {
        Chat chat = Chat.getInstance();

        // Verificar si el chat ya tiene un nombre
        if (chat.getChatname().isEmpty()) {
            chat.setChatname(chatname);

            // Intentar cargar el chat existente
            chat.loadChat();

            // Si no se pudo cargar, guardar el chat como nuevo
            if (chat.getChatname().isEmpty()) {
                chat.saveChat(); // Guardar el chat solo si no tiene un nombre
            }
        }

        return chat;
    }


    private User getUserFromUsersList(String username) {
        Users users = Users.getInstance();
        users.loadUsers();
        for (User existingUser : users.getMyusers()) {
            if (existingUser.getNickname().equals(username)) {
                return existingUser;
            }
        }
        return null;
    }

    public void userWritesMessage(Chat chat, User user, String messageText) {
        Message message = new Message(user.getNickname(), messageText, new Date());
        chat.getMessages().add(message);
        chat.saveChat(); // Guardar el chat con el nuevo mensaje

    }




    public void userJoinsChat(Chat chat, String username) {
        Users users = Users.getInstance();
        users.loadUsers();

        User userToJoin = getUserFromUsersList(users, username);
        if (userToJoin != null) {
            chat.getUsers().add(userToJoin);
            chat.saveChat(); // Guardar el chat con el usuario que se unió
        } else {
            System.out.println("El usuario no existe en la lista.");
        }
    }

    private User getUserFromUsersList(Users users, String username) {
        for (User existingUser : users.getMyusers()) {
            if (existingUser.getNickname().equals(username)) {
                return existingUser;
            }
        }
        return null;
    }


    public void userLeavessChat(Chat chat, User user) {
        chat.getUsers().remove(user);
        chat.saveChat(); // Guardar el chat sin el usuario que se fue
    }

    // Verificar si un usuario está en el chat
    public boolean isUserInChat(Chat chat, String username) {
        for (User user : chat.getUsers()) {
            if (user.getNickname().equals(username)) {
                return true;
            }
        }
        return false;
    }



}
