package com.iesfranciscodelosrios.chatproyectjaxb.model.Dao;


import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.User;
import com.iesfranciscodelosrios.chatproyectjaxb.model.dto.Users;

public class UserDAO {

    // Método para crear un nuevo usuario
    public static void createUser(User user) {
        Users users = Users.getInstance();
        users.loadUsers(); // Cargar usuarios desde el archivo XML

        if (!userExists(users, user.getNickname())) {
            users.addUser(user);
            users.saveUsers(); // Guardar la lista actualizada en el archivo XML
        } else {
            // Manejar el caso donde el nickname ya existe
            System.out.println("El usuario con el nickname '" + user.getNickname() + "' ya está en la lista.");
            // Puedes lanzar una excepción o mostrar un mensaje de error, según tus necesidades
        }
    }


    // Método para verificar si un usuario con el mismo nickname ya existe en la lista
    private static boolean userExists(Users users, String nickname) {
        for (User existingUser : users.getMyusers()) {
            if (existingUser.getNickname().equals(nickname)) {
                return true; // El usuario con el mismo nickname ya existe en la lista
            }
        }
        return false; // El usuario no existe en la lista
    }




}

