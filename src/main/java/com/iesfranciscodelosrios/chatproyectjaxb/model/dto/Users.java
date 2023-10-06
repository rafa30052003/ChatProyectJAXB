package com.iesfranciscodelosrios.chatproyectjaxb.model.dto;



import com.iesfranciscodelosrios.chatproyectjaxb.conexion.XMLmanager;
import com.iesfranciscodelosrios.chatproyectjaxb.services.ConfigManager;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "myusers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement(name = "User")
    private List<User> myusers;

    // Instancia única de Users (patrón Singleton)
    private static Users instance;
    String path = ConfigManager.readSharedFolderPath();
    // Ruta del archivo XML
    private static final String XML_FILE = "xml/users.xml";

    // Constructor privado para evitar la creación de instancias desde fuera de la clase.
    private Users() {
        myusers = new ArrayList<>();

    }

    // Método para obtener la instancia única de Users
    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public List<User> getMyusers() {

        return myusers;
    }

    public void setMyusers(List<User> myusers) {

        this.myusers = myusers;
    }

    // Método para añadir un usuario a la lista
    public void addUser(User user) {
        myusers.add(user);
        saveUsers(); // Guardar la lista actualizada cuando se agrega un usuario
    }

    // Método para guardar la lista en el archivo XML
    public void saveUsers() {
        XMLmanager.writeXML(this, XML_FILE);
    }

    // Método para cargar la lista desde el archivo XML
    public void loadUsers() {
        if (myusers.isEmpty()) {
            Users loadedUsers = XMLmanager.readXML(this, XML_FILE);
            if (loadedUsers != null) {
                this.myusers = loadedUsers.getMyusers();
            }
        }
    }

}
