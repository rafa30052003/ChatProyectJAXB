package com.iesfranciscodelosrios.chatproyectjaxb.prueba;

import com.iesfranciscodelosrios.chatproyectjaxb.model.Dao.ChatDAO;
import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.Chat;
import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.User;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        User newUser7 = new User("pedro", new Date());
        ChatDAO cd = new ChatDAO();
        Chat newchat = cd.createChat("motor");
        cd.isUserInChat(newchat,newUser7.getNickname());
        cd.userWritesMessage(newchat,newUser7,"hola");
        cd.userWritesMessage(newchat,newUser7,"mundo");
    }


}

