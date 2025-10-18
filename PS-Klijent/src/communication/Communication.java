/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.Klijent;
import model.StavkaUgovora;
import model.Ugovor;
import operacije.Operacija;
import transfer.Receiver;
import transfer.Request;
import transfer.Response;
import transfer.Sender;

/**
 *
 * @author Marko
 */
public class Communication {

    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    private static Communication instance;

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    private Communication() {
        try {
            socket = new Socket("localhost", 9000);
            receiver = new Receiver(socket);
            sender = new Sender(socket);
        } catch (IOException ex) {
            System.out.println("Greska prilikom povezivanja na server: " + ex.getMessage());
        }
    }

    //Operacije
    public Klijent login(Klijent klijent) {
        Request request = new Request(klijent, Operacija.PRIJAVI_KLIJENT);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (Klijent) response.getParams();
    }
    
    public int izmeniKlijenta(Klijent klijent) {
        Request request = new Request(klijent, Operacija.IZMENI_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }
    
    public List<Ugovor> vratiSveUgovore() {
        Request request = new Request(null, Operacija.VRATI_SVE_UGOVORE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (List<Ugovor>) response.getParams();
    }
    
    public List<StavkaUgovora> vratiSveStavke() {
        Request request = new Request(null, Operacija.VRATI_SVE_STAVKE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (List<StavkaUgovora>) response.getParams();
    }

}
