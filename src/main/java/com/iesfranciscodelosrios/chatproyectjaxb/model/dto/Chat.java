package com.iesfranciscodelosrios.chatproyectjaxb.model.dto;

import com.iesfranciscodelosrios.chatproyectjaxb.conexion.XMLmanager;
import com.iesfranciscodelosrios.chatproyectjaxb.services.ChatUpdater;
import com.iesfranciscodelosrios.chatproyectjaxb.services.ConfigManager;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@XmlRootElement(name = "chat")
@XmlType(propOrder = { "chatname", "messages", "users", "filename" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Chat {
    @XmlElement(name = "messages")
    private List<Message> messages;
    @XmlElement(name = "users")
    private List<User> users;
    @XmlElement(name = "name")
    private String chatname;
    @XmlElement(name = "filename") // Campo para almacenar el nombre de archivo
    private String filename;

    private static Chat instance;

    private Chat() {
        messages = new ArrayList<>();
        users = new ArrayList<>();
        chatname = "";
        filename = "";
    }

    public static synchronized Chat getInstance() {
        if (instance == null) {
            instance = new Chat();
        }
        return instance;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getChatname() {
        return chatname;
    }

    public void setChatname(String chatname) {
        this.chatname = chatname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void saveChat() {
        if (filename.isEmpty()) {
            // Si el nombre de archivo está vacío, genera un nombre de archivo único
            filename = generateUniqueFileName();
        }
        XMLmanager.writeXML(this, filename);
    }

    public void loadChat() {
        if (!filename.isEmpty()) {
            Chat loadedChat = XMLmanager.readXML(this, filename);
            if (loadedChat != null) {
                // Cargar los datos del chat desde el archivo especificado
                // ...
            }
        }
    }

    private String generateUniqueFileName() {
        // Usar el nombre del chat como parte del nombre de archivo
        String sharedFolderPath = ConfigManager.readSharedFolderPath();
        if (sharedFolderPath != null && !sharedFolderPath.isEmpty()) {
            return sharedFolderPath + "chat_" + chatname + ".xml";
        } else {
            return "xml/chat_" + chatname + ".xml";
        }
    }

    public void startPeriodicUpdate() {
        Timer timer = new Timer();
        long delay = 0; // Tiempo de espera antes de la primera ejecución
        long period = 10000; // Intervalo de ejecución en milisegundos (10 segundos)

        // Programar la ejecución periódica de la actualización del chat
        timer.scheduleAtFixedRate(new ChatUpdater(this), delay, period);
    }
}
