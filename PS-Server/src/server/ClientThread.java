/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.net.Socket;
import operacije.Operacija;
import transfer.Receiver;
import transfer.Request;
import transfer.Response;
import transfer.Sender;

/**
 *
 * @author Marko
 */
public class ClientThread extends Thread{
    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
      
    public ClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (socket != null && !socket.isClosed()) {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            if (request == null) break;
            switch (request.getOperacija()) {
                case Operacija.O1:
                    
                    break;
                case Operacija.O2:
                    
                    break;
                case Operacija.O3:
                    
                    break;
                default:
                    throw new AssertionError();
            }
            
            sender.send(response);
        }
    }
    
    
}
