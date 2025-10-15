/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Marko
 */
public class Receiver {

    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public Object receive() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        } catch (IOException ex) {
            System.out.println("Veza prekinuta");
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex1) {
                    System.out.println("Greka prilikom zatvaranja soketa");
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Doslo je do greske prilikom prijema");
        }
        return null;
    }
}
